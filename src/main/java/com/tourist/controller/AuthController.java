package com.tourist.controller;




import com.tourist.dto.LoginRequest;
import com.tourist.dto.RegisterRequest;
import com.tourist.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;



    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request){


        return ResponseEntity.ok(
                authService.register(request)
        );

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest request){

        System.out.println("Login API called");


        return ResponseEntity.ok(
                authService.login(request)
        );

    }
}