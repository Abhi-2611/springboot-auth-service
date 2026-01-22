package com.example.app.backend.school.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.school.dao.StudentAttendanceDao;
import com.example.app.backend.school.service.StudentAttendanceService;

@RestController
@RequestMapping("/teacher")
@PreAuthorize("hasRole('TEACHER')")
public class TeacherAttendanceController {

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @PostMapping("/markAttendance")
    public MessageResponse markAttendance(@RequestBody StudentAttendanceDao studentAttendanceDao) {
        return studentAttendanceService.markAttendance(studentAttendanceDao);
    }

    @GetMapping("/viewClassAttendance")
    public List<StudentAttendanceDao> viewClassAttendance(@RequestParam Long classId, @RequestParam LocalDate date) {
        return studentAttendanceService.getClassAttendance(classId, date);
    }
}

