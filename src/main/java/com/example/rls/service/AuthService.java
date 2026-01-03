package com.example.rls.service;

import com.example.rls.dao.JwtResponse;
import com.example.rls.dao.LoginRequest;
import com.example.rls.dao.MessageResponse;
import com.example.rls.dao.RegisterRequest;

public interface AuthService {

    MessageResponse register (RegisterRequest registerRequest);
    
    JwtResponse login (LoginRequest loginRequest);

    MessageResponse logout(String authorizationHeader);

}