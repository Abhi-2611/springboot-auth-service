package com.example.app.backend.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.school.dao.TeacherClassMappingDao;
import com.example.app.backend.school.service.TeacherClassMappingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/teacherClassMapping")
public class AdminTeacherClassMappingController {
    
    @Autowired
    private TeacherClassMappingService teacherClassMappingService;

    @PostMapping("/assignTeacherToClass")
    @PreAuthorize("hasAnyRole('ADMIN','PRINCIPAL')")
    public MessageResponse assignTeacherToClass(@Valid @RequestBody TeacherClassMappingDao teacherClassMappingDao) {
        return teacherClassMappingService.assignTeacherToClass(teacherClassMappingDao);
    }

}
