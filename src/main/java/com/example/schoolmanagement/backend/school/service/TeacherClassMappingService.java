package com.example.schoolmanagement.backend.school.service;

import java.util.List;

import com.example.schoolmanagement.backend.auth.dao.MessageResponse;
import com.example.schoolmanagement.backend.school.dao.TeacherClassMappingDao;
import com.example.schoolmanagement.backend.school.entity.TeacherClassMapping;

public interface TeacherClassMappingService {
    
    MessageResponse assignTeacherToClass(TeacherClassMappingDao teacherClassMappingDao);

    List<TeacherClassMapping> getAllTeacherClassMapping();

}
