package com.clienthub.crm.clienthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clienthub.crm.clienthub.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameContainingIgnoreCase(String fragment);
}
