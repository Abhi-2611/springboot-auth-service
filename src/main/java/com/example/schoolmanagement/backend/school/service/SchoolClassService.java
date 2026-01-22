package com.example.schoolmanagement.backend.school.service;

import com.example.schoolmanagement.backend.school.dao.SchoolClassDao;
import com.example.schoolmanagement.backend.school.entity.SchoolClass;

public interface SchoolClassService {
    
    SchoolClass createClass(SchoolClassDao schoolClassDao);
    
    // SchoolClass getById(Long classId);

}
