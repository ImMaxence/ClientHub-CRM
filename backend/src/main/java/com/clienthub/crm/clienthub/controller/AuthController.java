package com.clienthub.crm.clienthub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clienthub.crm.clienthub.model.User;
import com.clienthub.crm.clienthub.dto.AuthResponse;
import com.clienthub.crm.clienthub.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody User user) {
        AuthResponse response = authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User loginUser) {
        AuthResponse response = authService.login(loginUser.getEmail(), loginUser.getPassword());
        return ResponseEntity.ok(response);
    }
}