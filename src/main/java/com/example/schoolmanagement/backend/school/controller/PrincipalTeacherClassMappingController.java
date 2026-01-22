package com.example.schoolmanagement.backend.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.schoolmanagement.backend.school.entity.TeacherClassMapping;
import com.example.schoolmanagement.backend.school.service.TeacherClassMappingService;

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
