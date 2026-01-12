package com.example.app.sms.service;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.SubjectDao;

public interface SubjectService {
    
    MessageResponse createSubject(SubjectDao subjectDao);
}
