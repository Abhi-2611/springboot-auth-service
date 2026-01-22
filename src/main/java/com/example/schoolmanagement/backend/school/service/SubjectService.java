package com.example.schoolmanagement.backend.school.service;

import com.example.schoolmanagement.backend.auth.dao.MessageResponse;
import com.example.schoolmanagement.backend.school.dao.SubjectDao;

public interface SubjectService {
    
    MessageResponse createSubject(SubjectDao subjectDao);
}
