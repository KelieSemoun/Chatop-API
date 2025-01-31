package com.openclassrooms.backend.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.backend.service.JWTService;


@RestController
public class LoginController {


    private JWTService jwtService;
    
    public LoginController(JWTService jwtService) {
        this.jwtService = jwtService;
    }
    
    @PostMapping("/auth/login")
    public String getToken(Authentication authentication) {
                //String token = jwtService.generateToken(authentication);
                return "token";
    }
    
}