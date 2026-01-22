package com.example.app.backend.school.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.backend.school.dao.StudentAttendanceDao;
import com.example.app.backend.school.service.StudentAttendanceService;

@RestController
@RequestMapping("/principal")
@PreAuthorize("hasRole('PRINCIPAL')")
public class PrincipalAttendanceController {
    
    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @GetMapping("/viewClassAttendance")
    public List<StudentAttendanceDao> viewClassAttendance(@RequestParam Long classId, @RequestParam LocalDate date) {
        return studentAttendanceService.getClassAttendance(classId, date);
    }
}
