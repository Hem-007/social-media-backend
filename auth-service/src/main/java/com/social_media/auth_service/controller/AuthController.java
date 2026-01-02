package com.social_media.auth_service.controller;



import com.social_media.auth_service.Dto.AuthResponse;
import com.social_media.auth_service.Dto.LoginRequest;
import com.social_media.auth_service.Dto.RegisterRequest;
import com.social_media.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }

}
