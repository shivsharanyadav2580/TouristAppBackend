package com.tourist.service;

import com.tourist.dto.RegisterRequest;
import com.tourist.dto.UserResponse;
import com.tourist.entity.User;
import com.tourist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse registerUser(RegisterRequest request) {
        if(userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setPhoneNumber(request.phoneNumber());
        user.setRole("USER");
        User saveUser = userRepository.save(user);
        return mapToResponse(saveUser);

        // Implement user registration logic here
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return mapToResponse(user);
    }

    // update User
    public UserResponse updateUser(Long id, RegisterRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setPhoneNumber(request.phoneNumber());
        User updatedUser = userRepository.save(user);
        return mapToResponse(updatedUser);
    }

    //Delete User
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }


    //helper method to map User entity to a response DTO (if needed)
    private UserResponse mapToResponse(User saveUser) {
        return new UserResponse(
                saveUser.getId(),
                saveUser.getFullName(),
                saveUser.getEmail(),
                saveUser.getPhoneNumber(),
                saveUser.getRole()
        );
    }
}

