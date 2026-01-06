package com.example.app.rls.service;

import java.util.List;

import com.example.app.rls.entity.Role;

public interface RoleService {
    
    Role getDefaultRole();

    Role createRole(String rolename);
    
    List<Role> getAllRoles();
    
}