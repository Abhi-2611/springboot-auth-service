package com.example.schoolmanagement.backend.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolmanagement.backend.auth.dao.RoleDao;
import com.example.schoolmanagement.backend.auth.entity.Role;
import com.example.schoolmanagement.backend.auth.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @PostMapping("/createRole")
    @ResponseStatus(HttpStatus.CREATED)
    public Role createRole (@Valid @RequestBody RoleDao roleDao) {
        return roleService.createRole(roleDao.getRolename());
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}