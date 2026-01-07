package com.example.app.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.sms.entity.Student;
import com.example.app.sms.entity.Teacher;
import com.example.app.sms.service.StudentService;
import com.example.app.sms.service.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;


    @GetMapping("/students")
    @PreAuthorize("hasRole('TEACHER')")
    public List<Student> getMyStudents(Authentication authentication) {

        Long userId = (Long) authentication.getDetails();
        Teacher teacher = teacherService.getTeacherByUserId(userId);
        return studentService.getStudentsForTeacher(teacher.getId());
    }
    
}
