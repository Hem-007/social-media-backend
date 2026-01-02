package com.social_media.auth_service.service;

import com.social_media.auth_service.Dto.AuthResponse;
import com.social_media.auth_service.Dto.LoginRequest;
import com.social_media.auth_service.Dto.RegisterRequest;
import com.social_media.auth_service.model.User;
import com.social_media.auth_service.repository.UserRepository;
import com.social_media.auth_service.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null, "Email already registered!");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            return new AuthResponse(null, "Username already taken!");
        }

        // Convert DTO to Entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return new AuthResponse(null, "User registered successfully!");
    }

    public AuthResponse loginUser(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            return new AuthResponse(null, "Email not found!");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse(null, "Invalid password!");
        }

        // real JWT token
        String token = jwtUtil.generateToken(user.getId(), user.getUsername(),user.getEmail());
        return new AuthResponse(token, "Login successful!");
    }
}
