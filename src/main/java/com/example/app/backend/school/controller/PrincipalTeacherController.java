package com.example.app.backend.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.app.backend.school.dao.ResponseDao;
import com.example.app.backend.school.service.TeacherService;

@RestController
@RequestMapping("/api/principal/teachers")
public class PrincipalTeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getAllTeachers")
    @PreAuthorize("hasAnyRole('PRINCIPAL','ADMIN')")
    public ResponseDao getAllTeacher(
        @RequestParam(required = false) Integer pageNo,
        @RequestParam(required = false) Integer pageSize,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "DESC") String sortDir) {
        return teacherService.getAllTeachers(pageNo, pageSize, sortBy, sortDir);
    }
}
