package com.example.rls.service;

import java.util.List;

import com.example.rls.entity.Role;

public interface RoleService {
    
    Role getDefaultRole();

    Role createRole(String rolename);
    
    List<Role> getAllRoles();
    
}