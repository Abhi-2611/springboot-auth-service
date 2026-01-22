package com.example.schoolmanagement.backend.school.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schoolmanagement.backend.common.exception.DuplicateResourceException;
import com.example.schoolmanagement.backend.school.dao.SchoolClassDao;
import com.example.schoolmanagement.backend.school.entity.SchoolClass;
import com.example.schoolmanagement.backend.school.repository.SchoolClassRepository;
import com.example.schoolmanagement.backend.school.service.SchoolClassService;

@Service
public class SchoolClassServiceImpl implements SchoolClassService  {

    @Autowired
    private SchoolClassRepository schoolClassRepository;


    @Override
    public SchoolClass createClass(SchoolClassDao schoolClassDao) {
    
        schoolClassRepository.findByClassNameAndSectionAndAcademicYear(
            schoolClassDao.getClassName(), schoolClassDao.getSection(), schoolClassDao.getAcademicYear())
            .ifPresent(sc -> {throw new DuplicateResourceException("Class already exists");} );
        
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassName(schoolClassDao.getClassName());
        schoolClass.setSection(schoolClassDao.getSection());
        schoolClass.setAcademicYear(schoolClassDao.getAcademicYear());
        schoolClass.setActiveFlag(schoolClassDao.getActiveFlag());

        return schoolClassRepository.save(schoolClass);
    }
    
}
