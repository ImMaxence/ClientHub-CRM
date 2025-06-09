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
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cet email est déjà utilisé");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // avatarUrl reste null
        return userRepository.save(user);
    }

    /**
     * Mise à jour JSON‐only : ne touche pas à avatarUrl.
     */
    public User updateUser(Long id, User userDetails) {
        User u = getUserById(id);
        if (!u.getEmail().equals(userDetails.getEmail())
                && userRepository.existsByEmail(userDetails.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cet email est déjà utilisé");
        }
        u.setUsername(userDetails.getUsername());
        u.setEmail(userDetails.getEmail());

        if (!userDetails.getPassword().isEmpty()) {
            u.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        // avatarUrl inchangé
        return userRepository.save(u);
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
}
