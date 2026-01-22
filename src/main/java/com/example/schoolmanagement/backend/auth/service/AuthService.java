package com.example.schoolmanagement.backend.auth.service;

import com.example.schoolmanagement.backend.auth.dao.JwtResponse;
import com.example.schoolmanagement.backend.auth.dao.LoginRequest;
import com.example.schoolmanagement.backend.auth.dao.MessageResponse;
import com.example.schoolmanagement.backend.auth.dao.RegisterRequest;

public interface AuthService {

    MessageResponse register (RegisterRequest registerRequest);
    
    JwtResponse login (LoginRequest loginRequest);

    MessageResponse logout(String authorizationHeader);

}