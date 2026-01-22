package com.example.schoolmanagement.backend.school.service;

import java.util.List;

import com.example.schoolmanagement.backend.auth.dao.MessageResponse;
import com.example.schoolmanagement.backend.school.dao.TeacherClassSubjectMappingDao;

public interface TeacherClassSubjectMappingService {

    MessageResponse assignSubjectToTeacher(TeacherClassSubjectMappingDao teacherClassSubjectMappingDao);
    
    List<TeacherClassSubjectMappingDao> getMySubjectsAndClasses();

    List<TeacherClassSubjectMappingDao> getAllSubjectMappings();

}

