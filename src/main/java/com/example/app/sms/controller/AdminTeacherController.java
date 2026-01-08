package com.example.app.sms.controller;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.TeacherDao;
import com.example.app.sms.service.TeacherService;

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
