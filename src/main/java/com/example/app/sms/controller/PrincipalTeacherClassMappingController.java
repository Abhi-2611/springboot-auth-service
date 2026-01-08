package com.example.app.sms.controller;

import com.example.app.sms.entity.TeacherClassMapping;
import com.example.app.sms.service.TeacherClassMappingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/principal/teacherClassMapping")
public class PrincipalTeacherClassMappingController {

    @Autowired
    private TeacherClassMappingService teacherClassMappingService;

    @GetMapping("/getAllTeacherClassMappings")
    @PreAuthorize("hasAnyRole('PRINCIPAL','ADMIN')")
    public List<TeacherClassMapping> getAllTeacherClassMapping() {
        return teacherClassMappingService.getAllTeacherClassMapping();
    }
}
