package com.clienthub.crm.clienthub;

import com.clienthub.crm.clienthub.model.User;
import com.clienthub.crm.clienthub.model.User.Role;
import com.clienthub.crm.clienthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.annotation.PostConstruct;

@Configuration
public class InitialAdminConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdminIfNotExists() {
        boolean hasAdmin = userRepository.findAll().stream()
                .anyMatch(u -> u.getRole() == Role.ROLE_ADMIN);
        if (!hasAdmin) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@clienthub.local");
            admin.setPassword(passwordEncoder.encode("admin1234"));
            admin.setRole(Role.ROLE_ADMIN);
            userRepository.save(admin);
            System.out.println("[INIT] Admin par défaut créé: admin@clienthub.local / admin1234");
        }
    }
}
