package com.example.schoolmanagement.backend.auth.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schoolmanagement.backend.auth.entity.Role;
import com.example.schoolmanagement.backend.auth.repository.RoleRepository;
import com.example.schoolmanagement.backend.auth.service.RoleService;
import com.example.schoolmanagement.backend.common.exception.DuplicateResourceException;
import com.example.schoolmanagement.backend.common.exception.ResourceNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getDefaultRole() {
        return roleRepository.findByRolename("ROLE_USER")
            .orElseThrow(() -> new ResourceNotFoundException("Default role ROLE_USER not found"));
    }

    @Override
    public Role createRole(String rolename) {

        if (roleRepository.existsByRolename(rolename.toUpperCase())) {
            throw new DuplicateResourceException("Role already exists");
        }

        Role role = Role.builder().rolename(rolename.toUpperCase()).build();

        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    
}
