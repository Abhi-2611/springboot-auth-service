package com.example.schoolmanagement.backend.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.schoolmanagement.backend.school.dao.ResponseDao;
import com.example.schoolmanagement.backend.school.service.StudentService;

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
