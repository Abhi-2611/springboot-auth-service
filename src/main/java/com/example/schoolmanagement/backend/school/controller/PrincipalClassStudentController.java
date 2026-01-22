package com.example.schoolmanagement.backend.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.schoolmanagement.backend.school.entity.Student;
import com.example.schoolmanagement.backend.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/principal/students")
public class PrincipalClassStudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getStudentsByClass")
    @PreAuthorize("hasAnyRole('PRINCIPAL','ADMIN')")
    public List<Student> getStudentsByClass(@RequestParam Long classId) {
        return studentService.getStudentsByClassId(classId);
    }
    
}
