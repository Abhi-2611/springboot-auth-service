package com.example.app.backend.auth.service;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.auth.dao.ResponseDao;
import com.example.app.backend.auth.dao.UserDao;

public interface UserService {
    
    UserDao getById(Long id);
    
    ResponseDao getAll();

    MessageResponse assignRoleToUser(Long id, String rolename);

}