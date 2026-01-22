package com.example.schoolmanagement.backend.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.schoolmanagement.backend.auth.dao.MessageResponse;
import com.example.schoolmanagement.backend.school.dao.TeacherDao;
import com.example.schoolmanagement.backend.school.service.TeacherService;

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
