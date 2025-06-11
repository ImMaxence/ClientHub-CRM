package com.clienthub.crm.clienthub.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.clienthub.crm.clienthub.model.User;
import com.clienthub.crm.clienthub.dto.UserRequest;
import com.clienthub.crm.clienthub.dto.UserResponse;
import com.clienthub.crm.clienthub.dto.mapper.UserResponseMapper;
import com.clienthub.crm.clienthub.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable).map(UserResponseMapper::toDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or #id == authentication.principal.id")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(UserResponseMapper.toDto(userService.getUserById(id)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public List<UserResponse> searchByName(@RequestParam("q") String q) {
        return userService.searchByName(q).stream().map(UserResponseMapper::toDto).toList();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        User created = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseMapper.toDto(created));
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<UserResponse> updateUserJson(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        User updated = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(UserResponseMapper.toDto(updated));
    }

    @PostMapping(path = "/{id}/avatar")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<UserResponse> uploadAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        User updated = userService.oneShotUploadAvatar(id, file);
        return ResponseEntity.ok(UserResponseMapper.toDto(updated));
    }

    // Endpoint pour changer le rôle d'un utilisateur (ADMIN seulement)
    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateUserRole(@PathVariable Long id, @RequestParam("role") User.Role newRole) {
        User updated = userService.updateUserRole(id, newRole);
        return ResponseEntity.ok(UserResponseMapper.toDto(updated));
    }
}
