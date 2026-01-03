package com.example.rls.service;

import com.example.rls.dao.MessageResponse;
import com.example.rls.dao.ResponseDao;
import com.example.rls.dao.UserDao;

public interface UserService {
    
    UserDao getById(Long id);
    
    ResponseDao getAll();

    MessageResponse assignRoleToUser(Long id, String rolename);

}