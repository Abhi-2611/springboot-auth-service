package com.example.schoolmanagement.backend.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolmanagement.backend.auth.dao.MessageResponse;
import com.example.schoolmanagement.backend.school.dao.StudentAttendanceDao;
import com.example.schoolmanagement.backend.school.service.StudentAttendanceService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminAttendanceController {

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @PostMapping("/correctAttendance")
    public MessageResponse correctAttendance(@RequestBody StudentAttendanceDao studentAttendanceDao) {

        return studentAttendanceService.correctAttendance(studentAttendanceDao);
    }
}

