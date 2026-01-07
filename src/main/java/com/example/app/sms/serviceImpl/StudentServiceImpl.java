package com.example.app.sms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.sms.entity.Student;
import com.example.app.sms.repository.StudentRepository;
import com.example.app.sms.repository.TeacherClassMappingRepository;
import com.example.app.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherClassMappingRepository teacherClassMappingRepository;


    @Override
    public List<Student> getStudentsForTeacher(Long teacherId) {
    
        List<Long> classIds = teacherClassMappingRepository.findClassIdsByTeacherId(teacherId);

        if (classIds.isEmpty()) {
            return List.of();
        }
        return studentRepository.findByClassIdIn(classIds);
    }
    
}
