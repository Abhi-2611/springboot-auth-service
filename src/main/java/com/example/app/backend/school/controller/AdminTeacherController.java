package com.example.app.backend.school.controller;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.school.dao.TeacherDao;
import com.example.app.backend.school.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/teachers")
public class AdminTeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/createTeacher")
    @PreAuthorize("hasAnyRole('ADMIN','PRINCIPAL')")
    public MessageResponse createTeacher(@RequestBody TeacherDao teacherDao) {
        return teacherService.createTeacher(teacherDao);
    }

}
