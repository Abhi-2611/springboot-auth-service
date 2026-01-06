package com.example.app.rls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.rls.dao.JwtResponse;
import com.example.app.rls.dao.LoginRequest;
import com.example.app.rls.dao.MessageResponse;
import com.example.app.rls.dao.RegisterRequest;
import com.example.app.rls.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse UserRegistration (@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
    
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse UserLogin (@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse logout(@RequestHeader("Authorization") String authHeader) {
        return authService.logout(authHeader);
    }

}