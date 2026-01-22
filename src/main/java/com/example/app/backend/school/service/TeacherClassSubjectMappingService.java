package com.example.app.backend.school.service;

import java.util.List;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.school.dao.TeacherClassSubjectMappingDao;

public interface TeacherClassSubjectMappingService {

    MessageResponse assignSubjectToTeacher(TeacherClassSubjectMappingDao teacherClassSubjectMappingDao);
    
    List<TeacherClassSubjectMappingDao> getMySubjectsAndClasses();

    List<TeacherClassSubjectMappingDao> getAllSubjectMappings();

}

