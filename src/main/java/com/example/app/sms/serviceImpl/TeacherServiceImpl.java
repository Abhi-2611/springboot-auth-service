package com.example.app.sms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.sms.entity.Teacher;
import com.example.app.sms.repository.TeacherRepository;
import com.example.app.sms.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher getTeacherByUserId(Long userId) {
        return teacherRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("Teacher not found for userId: " + userId));
    }
    
}
