package com.example.app.backend.school.service;

import java.util.List;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.school.dao.TeacherClassMappingDao;
import com.example.app.backend.school.entity.TeacherClassMapping;

public interface TeacherClassMappingService {
    
    MessageResponse assignTeacherToClass(TeacherClassMappingDao teacherClassMappingDao);

    List<TeacherClassMapping> getAllTeacherClassMapping();

}
