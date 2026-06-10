package com.tourist.controller;

import com.tourist.dto.RegisterRequest;
import com.tourist.dto.UserResponse;
import com.tourist.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/api/register")

    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody RegisterRequest request
            ){
        UserResponse response =  service.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {

        return ResponseEntity.ok(service.getUserById(id));
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(
                service.getAllUsers()
        );
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody RegisterRequest request
    ) {
        UserResponse response = service.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

        @DeleteMapping("/api/users/{id}")

    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
