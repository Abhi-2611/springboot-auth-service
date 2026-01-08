package com.example.app.sms.controller;

import com.example.app.sms.entity.Student;
import com.example.app.sms.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
