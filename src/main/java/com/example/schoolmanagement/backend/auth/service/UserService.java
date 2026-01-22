package com.example.schoolmanagement.backend.auth.service;

import com.example.schoolmanagement.backend.auth.dao.MessageResponse;
import com.example.schoolmanagement.backend.auth.dao.ResponseDao;
import com.example.schoolmanagement.backend.auth.dao.UserDao;

public interface UserService {
    
    UserDao getById(Long id);
    
    ResponseDao getAll();

    MessageResponse assignRoleToUser(Long id, String rolename);

}