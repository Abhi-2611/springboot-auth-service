package com.example.app.backend.school.service;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.school.dao.SubjectDao;

public interface SubjectService {
    
    MessageResponse createSubject(SubjectDao subjectDao);
}
