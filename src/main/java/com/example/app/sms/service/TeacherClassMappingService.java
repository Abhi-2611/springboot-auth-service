package com.example.app.sms.service;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.TeacherClassMappingDao;

public interface TeacherClassMappingService {
    
    MessageResponse assignTeacherToClass(TeacherClassMappingDao teacherClassMappingDao);

}
