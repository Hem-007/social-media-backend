package com.social_media.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String securedEndpoint() {
        return "JWT verified — secure endpoint accessed!";
    }
}
