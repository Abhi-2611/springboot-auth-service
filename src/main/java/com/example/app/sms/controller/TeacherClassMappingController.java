package com.example.app.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.TeacherClassMappingDao;
import com.example.app.sms.service.TeacherClassMappingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/teacherClassMapping")
public class TeacherClassMappingController {
    
    @Autowired
    private TeacherClassMappingService teacherClassMappingService;

    @PostMapping("/assignTeacherToClass")
    @PreAuthorize("hasAnyRole('ADMIN','PRINCIPAL')")
    public MessageResponse assignTeacherToClass(@Valid @RequestBody TeacherClassMappingDao teacherClassMappingDao) {
        return teacherClassMappingService.assignTeacherToClass(teacherClassMappingDao);
    }

}
