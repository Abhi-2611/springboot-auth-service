package com.example.app.backend.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.backend.school.dao.TeacherClassSubjectMappingDao;
import com.example.app.backend.school.service.TeacherClassSubjectMappingService;

@RestController
@RequestMapping("/principal")
@PreAuthorize("hasRole('PRINCIPAL')")
public class PrincipalSubjectMappingController {

    @Autowired
    private TeacherClassSubjectMappingService teacherClassSubjectMappingService;

    @GetMapping("/subjectMappings")
    public List<TeacherClassSubjectMappingDao> getAllMappings() {
        return teacherClassSubjectMappingService.getAllSubjectMappings();
    }
}
