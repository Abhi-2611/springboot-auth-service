package com.example.app.sms.service;

import com.example.app.sms.dao.SchoolClassDao;
import com.example.app.sms.entity.SchoolClass;

public interface SchoolClassService {
    
    SchoolClass createClass(SchoolClassDao schoolClassDao);
    
    // SchoolClass getById(Long classId);

}
