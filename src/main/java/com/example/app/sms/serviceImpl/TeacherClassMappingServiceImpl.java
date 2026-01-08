package com.example.app.sms.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.TeacherClassMappingDao;
import com.example.app.sms.entity.TeacherClassMapping;
import com.example.app.sms.repository.SchoolClassRepository;
import com.example.app.sms.repository.TeacherClassMappingRepository;
import com.example.app.sms.repository.TeacherRepository;
import com.example.app.sms.service.TeacherClassMappingService;

@Service
public class TeacherClassMappingServiceImpl implements TeacherClassMappingService {

    @Autowired
    private TeacherClassMappingRepository teacherClassMappingRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Override
    public MessageResponse assignTeacherToClass(TeacherClassMappingDao teacherClassMappingDao) {
        
        teacherRepository.findById(teacherClassMappingDao.getTeacherId())
            .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherClassMappingDao.getTeacherId()));

        schoolClassRepository.findById(teacherClassMappingDao.getClassId())
            .orElseThrow(() -> new RuntimeException("Class not found with id: " + teacherClassMappingDao.getClassId()));

        if (teacherClassMappingRepository
            .existsByTeacherIdAndClassId(teacherClassMappingDao.getTeacherId(), teacherClassMappingDao.getClassId())) {
            throw new RuntimeException("Teacher already assigned to this class");
        }

        TeacherClassMapping mapping = new TeacherClassMapping();
        mapping.setTeacherId(teacherClassMappingDao.getTeacherId());
        mapping.setClassId(teacherClassMappingDao.getClassId());
        mapping.setIsClassTeacher(Boolean.TRUE.equals(teacherClassMappingDao.getIsClassTeacher()));
        mapping.setAssignedDate(LocalDate.now());

        teacherClassMappingRepository.save(mapping);

        return MessageResponse.builder().message("Teacher Assigned to Class Successfully").build();
    }

    @Override
    public List<TeacherClassMapping> getAllTeacherClassMapping() {
        return teacherClassMappingRepository.findAll();
    }
    
}
