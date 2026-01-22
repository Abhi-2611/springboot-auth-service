package com.example.app.backend.auth.service;

import com.example.app.backend.auth.dao.JwtResponse;
import com.example.app.backend.auth.dao.LoginRequest;
import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.auth.dao.RegisterRequest;

public interface AuthService {

    MessageResponse register (RegisterRequest registerRequest);
    
    JwtResponse login (LoginRequest loginRequest);

    MessageResponse logout(String authorizationHeader);

}