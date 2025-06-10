package com.clienthub.crm.clienthub.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private String avatarUrl;
}
