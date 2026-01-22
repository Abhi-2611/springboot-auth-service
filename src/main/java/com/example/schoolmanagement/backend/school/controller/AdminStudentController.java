package com.example.schoolmanagement.backend.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.schoolmanagement.backend.auth.dao.MessageResponse;
import com.example.schoolmanagement.backend.school.dao.StudentDao;
import com.example.schoolmanagement.backend.school.service.StudentService;

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
