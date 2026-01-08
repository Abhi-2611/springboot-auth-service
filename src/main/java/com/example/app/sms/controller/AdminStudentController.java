package com.example.app.sms.controller;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.StudentDao;
import com.example.app.sms.service.StudentService;

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
