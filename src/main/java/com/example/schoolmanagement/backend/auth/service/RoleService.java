package com.example.schoolmanagement.backend.auth.service;

import java.util.List;

import com.example.schoolmanagement.backend.auth.entity.Role;

public interface RoleService {
    
    Role getDefaultRole();

    Role createRole(String rolename);
    
    List<Role> getAllRoles();
    
}