package com.example.app.rls.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.common.exception.ResourceNotFoundException;
import com.example.app.rls.dao.MessageResponse;
import com.example.app.rls.dao.ResponseDao;
import com.example.app.rls.dao.UserDao;
import com.example.app.rls.entity.Role;
import com.example.app.rls.entity.User;
import com.example.app.rls.repository.RoleRepository;
import com.example.app.rls.repository.UserRepository;
import com.example.app.rls.service.UserService;

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