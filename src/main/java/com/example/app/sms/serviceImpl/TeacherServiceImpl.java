package com.example.app.sms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.TeacherDao;
import com.example.app.sms.entity.Teacher;
import com.example.app.sms.repository.TeacherRepository;
import com.example.app.sms.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher getTeacherByUserId(Long userId) {
        return teacherRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("Teacher not found for userId: " + userId));
    }

    @Override
    public MessageResponse createTeacher(TeacherDao teacherDao) {
        
        teacherRepository.findByUserId(teacherDao.getUserId())
            .ifPresent(t -> {throw new RuntimeException("Teacher already exists for userId: " + teacherDao.getUserId());});

        Teacher teacher = new Teacher();
        teacher.setUserId(teacherDao.getUserId());
        teacher.setEmployeeCode(teacherDao.getEmployeeCode());
        teacher.setFirstName(teacherDao.getFirstName());
        teacher.setLastName(teacherDao.getLastName());
        teacher.setDesignation(teacherDao.getDesignation());
        teacher.setQualification(teacherDao.getQualification());
        teacher.setJoinDate(teacherDao.getJoinDate());
        teacher.setActiveFlag(teacherDao.getActiveFlag());

        teacherRepository.save(teacher);

        return MessageResponse.builder().message("Teacher added Successfully").build();
        
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    
}
