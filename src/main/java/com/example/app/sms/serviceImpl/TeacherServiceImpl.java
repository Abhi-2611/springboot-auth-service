package com.example.app.sms.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.common.exception.DuplicateResourceException;
import com.example.app.common.exception.ResourceNotFoundException;
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
            .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for userId: " + userId));
    }

    @Override
    public MessageResponse createTeacher(TeacherDao teacherDao) {
        
        teacherRepository.findByUserId(teacherDao.getUserId()).ifPresent(t -> {
            throw new DuplicateResourceException("Teacher already exists for userId: " + teacherDao.getUserId());});

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDao, teacher);
        teacherRepository.save(teacher);
        return MessageResponse.builder()
            .message("Teacher added Successfully").build();
        
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    
}
