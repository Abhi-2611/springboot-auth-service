package com.example.app.sms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.sms.dao.StudentAttendanceDao;
import com.example.app.sms.service.StudentAttendanceService;

@RestController
@RequestMapping("/teacher")
@PreAuthorize("hasRole('TEACHER')")
public class TeacherStudentAttendanceHistoryController {

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @GetMapping("/attendance/getStudentHistory")
    public List<StudentAttendanceDao> getStudentHistory(@RequestParam Long studentId,
        @RequestParam LocalDate from, @RequestParam LocalDate to) {
        return studentAttendanceService.getStudentAttendanceHistory(studentId, from, to);
    }
}

