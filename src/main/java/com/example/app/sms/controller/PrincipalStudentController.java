package com.example.app.sms.controller;

import com.example.app.sms.dao.ResponseDao;
import com.example.app.sms.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/principal/students")
public class PrincipalStudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getAllStudents")
    @PreAuthorize("hasAnyRole('PRINCIPAL','ADMIN')")
    public ResponseDao getAllStudents(
        @RequestParam(required = false) Integer pageNo,
        @RequestParam(required = false) Integer pageSize,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "DESC") String sortDir) {
        return studentService.getAllStudents(pageNo, pageSize, sortBy, sortDir);
    }

}
