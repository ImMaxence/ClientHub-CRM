package com.clienthub.crm.clienthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clienthub.crm.clienthub.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameContainingIgnoreCase(String fragment);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
