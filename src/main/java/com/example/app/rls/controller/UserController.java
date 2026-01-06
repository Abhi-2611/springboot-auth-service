package com.example.app.rls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.rls.dao.ResponseDao;
import com.example.app.rls.dao.UserDao;
import com.example.app.rls.service.UserService;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/getById")
    public UserDao getById(@RequestParam Long id) {
        return userService.getById(id);
    }
    
    @GetMapping("/getAll")
    public ResponseDao getAll() {
        return userService.getAll();
    }

    @PostMapping("/assignRole")
    public MessageResponse assignRoleToUser(
            @RequestParam Long userId,
            @RequestParam String rolename) {
        return userService.assignRoleToUser(userId, rolename);
    }

}
