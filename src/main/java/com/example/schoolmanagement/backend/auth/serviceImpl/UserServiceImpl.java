package com.example.schoolmanagement.backend.auth.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schoolmanagement.backend.auth.dao.MessageResponse;
import com.example.schoolmanagement.backend.auth.dao.ResponseDao;
import com.example.schoolmanagement.backend.auth.dao.UserDao;
import com.example.schoolmanagement.backend.auth.entity.Role;
import com.example.schoolmanagement.backend.auth.entity.User;
import com.example.schoolmanagement.backend.auth.repository.RoleRepository;
import com.example.schoolmanagement.backend.auth.repository.UserRepository;
import com.example.schoolmanagement.backend.auth.service.UserService;
import com.example.schoolmanagement.backend.common.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDao getById(Long id) {

        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toDao(user);
    }

    @Override
    public ResponseDao getAll() {

        ResponseDao responseDao = new ResponseDao();
        List<User> users = userRepository.findAll();
        responseDao.setUserList(
            users.stream().map(this::toDao).collect(Collectors.toList()));
        return responseDao;
    }

    private UserDao toDao (User user) {
        UserDao dao = new UserDao();
        BeanUtils.copyProperties(user, dao);
        return dao;
    }

    @Override
    public MessageResponse assignRoleToUser(Long id, String rolename) {

        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findByRolename(rolename)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        user.getRoles().add(role);
        userRepository.save(user);

        return MessageResponse.builder().message("Role assigned successfully").build();
        
    }

}