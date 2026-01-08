package com.example.app.sms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.sms.dao.SchoolClassDao;
import com.example.app.sms.entity.SchoolClass;
import com.example.app.sms.repository.SchoolClassRepository;
import com.example.app.sms.service.SchoolClassService;

@Service
public class SchoolClassServiceImpl implements SchoolClassService  {

    @Autowired
    private SchoolClassRepository schoolClassRepository;


    @Override
    public SchoolClass createClass(SchoolClassDao schoolClassDao) {
    
        schoolClassRepository.findByClassNameAndSectionAndAcademicYear(
            schoolClassDao.getClassName(), schoolClassDao.getSection(), schoolClassDao.getAcademicYear())
            .ifPresent(sc -> {throw new RuntimeException("Class already exists");} );
        
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassName(schoolClassDao.getClassName());
        schoolClass.setSection(schoolClassDao.getSection());
        schoolClass.setAcademicYear(schoolClassDao.getAcademicYear());
        schoolClass.setActiveFlag(schoolClassDao.getActiveFlag());

        return schoolClassRepository.save(schoolClass);
    }
    
}
