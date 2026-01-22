package com.example.app.backend.school.service;

import com.example.app.backend.school.dao.SchoolClassDao;
import com.example.app.backend.school.entity.SchoolClass;

public interface SchoolClassService {
    
    SchoolClass createClass(SchoolClassDao schoolClassDao);
    
    // SchoolClass getById(Long classId);

}
