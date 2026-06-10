package com.tourist.dto;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        String phoneNumber,
        String role
) {
}
