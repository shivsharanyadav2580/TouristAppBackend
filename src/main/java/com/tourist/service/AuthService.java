package com.tourist.service;

import com.tourist.dto.LoginRequest;
import com.tourist.dto.RegisterRequest;
import com.tourist.entity.User;
import com.tourist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;



    public String register(RegisterRequest request){


        User user = new User();


        user.setFullName(request.fullName());

        user.setEmail(request.email());

        user.setPhoneNumber(request.phoneNumber());


        user.setPassword(
                passwordEncoder.encode(request.password())
        );


        user.setRole("USER");


        userRepository.save(user);



        return "User Registered Successfully";

    }

    public String login(LoginRequest request){


        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )

        );


        return jwtService.generateToken(
                request.email()
        );


    }


}