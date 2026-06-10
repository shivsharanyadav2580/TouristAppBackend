package com.tourist.dto;

public record RegisterRequest(
        String fullName,
        String email,
        String password,
        String phoneNumber
) {}
