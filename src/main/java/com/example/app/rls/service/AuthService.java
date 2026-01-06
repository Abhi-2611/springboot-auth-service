package com.example.app.rls.service;

import com.example.app.rls.dao.JwtResponse;
import com.example.app.rls.dao.LoginRequest;
import com.example.app.rls.dao.MessageResponse;
import com.example.app.rls.dao.RegisterRequest;

public interface AuthService {

    MessageResponse register (RegisterRequest registerRequest);
    
    JwtResponse login (LoginRequest loginRequest);

    MessageResponse logout(String authorizationHeader);

}