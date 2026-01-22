package com.example.app.backend.school.controller;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.school.dao.StudentDao;
import com.example.app.backend.school.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/students")
public class AdminStudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/createStudent")
    @PreAuthorize("hasAnyRole('ADMIN','PRINCIPAL')")
    public MessageResponse createStudent(@RequestBody StudentDao studentDao) {
        return studentService.createStudent(studentDao);
    }

}
