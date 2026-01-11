package com.example.app.sms.controller;

import com.example.app.sms.dao.ResponseDao;
import com.example.app.sms.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
