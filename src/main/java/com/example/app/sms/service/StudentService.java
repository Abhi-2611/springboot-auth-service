package com.example.app.sms.service;

import java.util.List;

import com.example.app.sms.entity.Student;

public interface StudentService {
 
    List<Student> getStudentsForTeacher(Long teacherId);

}
