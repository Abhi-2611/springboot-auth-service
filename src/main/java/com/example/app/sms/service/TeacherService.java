package com.example.app.sms.service;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.TeacherDao;
import com.example.app.sms.entity.Teacher;

public interface TeacherService {

    Teacher getTeacherByUserId(Long userId);

    MessageResponse createTeacher(TeacherDao teacherDao);
    
}
