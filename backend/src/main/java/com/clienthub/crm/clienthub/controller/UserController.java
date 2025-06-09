package com.clienthub.crm.clienthub.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.clienthub.crm.clienthub.model.User;
import com.clienthub.crm.clienthub.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<User> searchByName(@RequestParam("q") String q) {
        return userService.searchByName(q);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> updateUserJson(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        User updated = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updated);
    }

    @PostMapping(path = "/{id}/avatar")
    public ResponseEntity<User> uploadAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        User updated = userService.oneShotUploadAvatar(id, file);
        return ResponseEntity.ok(updated);
    }
}
