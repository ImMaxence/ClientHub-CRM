package com.clienthub.crm.clienthub.dto.mapper;

import com.clienthub.crm.clienthub.dto.UserResponse;
import com.clienthub.crm.clienthub.model.User;

public class UserResponseMapper {
    public static UserResponse toDto(User user) {
        if (user == null) return null;
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setRole(user.getRole() != null ? user.getRole().name() : null);
        return dto;
    }
}
