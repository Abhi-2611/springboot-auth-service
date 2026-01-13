package com.example.app.sms.service;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.TeacherClassSubjectMappingDao;

public interface TeacherClassSubjectMappingService {

    MessageResponse assignSubjectToTeacher(TeacherClassSubjectMappingDao teacherClassSubjectMappingDao);
    
}

