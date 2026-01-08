package com.example.app.sms.controller;

import com.example.app.sms.entity.Student;
import com.example.app.sms.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/principal/students")
public class PrincipalStudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getAllStudents")
    @PreAuthorize("hasAnyRole('PRINCIPAL','ADMIN')")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
