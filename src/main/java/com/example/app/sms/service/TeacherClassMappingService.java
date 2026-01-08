package com.example.app.sms.service;

import java.util.List;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.TeacherClassMappingDao;
import com.example.app.sms.entity.TeacherClassMapping;

public interface TeacherClassMappingService {
    
    MessageResponse assignTeacherToClass(TeacherClassMappingDao teacherClassMappingDao);

    List<TeacherClassMapping> getAllTeacherClassMapping();

}
