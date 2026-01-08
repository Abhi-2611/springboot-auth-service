package com.example.app.sms.controller;

import com.example.app.sms.entity.Teacher;
import com.example.app.sms.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/principal/teachers")
public class PrincipalTeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getAllTeachers")
    @PreAuthorize("hasAnyRole('PRINCIPAL','ADMIN')")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
}
