package com.clienthub.crm.clienthub.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.clienthub.crm.clienthub.client.FastApiClient;
import com.clienthub.crm.clienthub.dto.UserRequest;
import com.clienthub.crm.clienthub.model.User;
import com.clienthub.crm.clienthub.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FastApiClient fastApiClient;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

    public void deleteUser(Long id) {
        User u = getUserById(id);
        userRepository.delete(u);
    }

    /**
     * Création JSON‐only : enregistre l’utilisateur (avatarUrl = null).
     */
    public User createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cet email est déjà utilisé");
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(User.Role.ROLE_USER);
        return userRepository.save(user);
    }

    /**
     * Mise à jour JSON‐only : ne touche pas à avatarUrl.
     */
    public User updateUser(Long id, UserRequest userRequest) {
        User user = getUserById(id);
        if (!user.getEmail().equals(userRequest.getEmail())
                && userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cet email est déjà utilisé");
        }
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        if (!userRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }

        // avatarUrl inchangé
        return userRepository.save(user);
    }

    /**
     * Étape 2 – Upload de l’avatar :
     * appelle FastAPI, récupère l’URL et met à jour avatarUrl.
     */
    public User oneShotUploadAvatar(Long id, MultipartFile file) {
        User u = getUserById(id);
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Fichier avatar manquant");
        }
        String avatarUrl = fastApiClient.uploadOneShot(file);
        u.setAvatarUrl(avatarUrl);
        return userRepository.save(u);
    }

    public List<User> searchByName(String fragment) {
        return userRepository.findByUsernameContainingIgnoreCase(fragment);
    }

    /**
     * Permet à un ADMIN de changer le rôle d'un utilisateur.
     * Empêche un admin de se retirer son propre rôle ADMIN.
     */
    public User updateUserRole(Long id, User.Role newRole) {
        User user = getUserById(id);
        // On pourrait récupérer l'utilisateur courant via SecurityContextHolder si besoin
        // Pour l'instant, on empêche juste de retirer le dernier admin (à améliorer si besoin)
        if (user.getRole() == User.Role.ROLE_ADMIN && newRole != User.Role.ROLE_ADMIN) {
            // Compter le nombre d'admins restants
            long nbAdmins = userRepository.findAll().stream().filter(u -> u.getRole() == User.Role.ROLE_ADMIN).count();
            if (nbAdmins <= 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossible de retirer le dernier ADMIN");
            }
        }
        user.setRole(newRole);
        return userRepository.save(user);
    }
}
