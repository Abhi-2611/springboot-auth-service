package com.example.app.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.StudentAttendanceDao;
import com.example.app.sms.service.StudentAttendanceService;

@RestController
@RequestMapping("/teacher")
@PreAuthorize("hasRole('TEACHER')")
public class TeacherAttendanceController {

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @PostMapping("/attendance")
    public MessageResponse markAttendance(@RequestBody StudentAttendanceDao studentAttendanceDao) {
        return studentAttendanceService.markAttendance(studentAttendanceDao);
    }
}

