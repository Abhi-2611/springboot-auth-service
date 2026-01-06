package com.example.app.rls.service;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.rls.dao.ResponseDao;
import com.example.app.rls.dao.UserDao;

public interface UserService {
    
    UserDao getById(Long id);
    
    ResponseDao getAll();

    MessageResponse assignRoleToUser(Long id, String rolename);

}