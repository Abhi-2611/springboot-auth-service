package com.example.app.sms.service;

import com.example.app.sms.entity.Teacher;

public interface TeacherService {

    Teacher getTeacherByUserId(Long userId);
    
}
