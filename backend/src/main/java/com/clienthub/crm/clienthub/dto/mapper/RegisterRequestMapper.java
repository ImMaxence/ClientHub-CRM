package com.clienthub.crm.clienthub.dto.mapper;

import com.clienthub.crm.clienthub.dto.RegisterRequest;
import com.clienthub.crm.clienthub.model.User;

public class RegisterRequestMapper {
    public static User toEntity(RegisterRequest dto) {
        if (dto == null) return null;
        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(User.Role.ROLE_USER); // Rôle par défaut
        return entity;
    }
}
