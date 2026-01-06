package com.example.app.rls.dao;

import java.util.Set;

import com.example.app.rls.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDao {
    
    private Long id;
    private String firstName;
    private String lastName; 
	private String username;
    private String email;
    private Set<Role> roles;
}
