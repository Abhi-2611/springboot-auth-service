package com.example.schoolmanagement.backend.school.controller;

import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolmanagement.backend.school.dao.StudentAttendanceDao;
import com.example.schoolmanagement.backend.school.service.StudentAttendanceService;

@RestController
@RequestMapping("/principal")
@PreAuthorize("hasRole('PRINCIPAL')")
public class PrincipalAttendanceReportController {

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @GetMapping("/attendance/report/monthly")
    public List<StudentAttendanceDao> getMonthlyReport(@RequestParam Long classId,
        @RequestParam Integer year, @RequestParam Integer month) {
        return studentAttendanceService.getMonthlyReport(classId, YearMonth.of(year, month));
    }

    @GetMapping("/attendance/report/classReport")
    public StudentAttendanceDao getClassReport(@RequestParam Long classId, 
        @RequestParam Integer year, @RequestParam Integer month) {
        return studentAttendanceService.getClassMonthlyReport(classId, YearMonth.of(year, month));
    }

    @GetMapping("/attendance/report/lowAttendance")
    public List<StudentAttendanceDao> getLowAttendanceReport(@RequestParam Long classId, 
        @RequestParam Integer year, @RequestParam Integer month, 
        @RequestParam(required = false) Double threshold) {
        return studentAttendanceService.getLowAttendanceReport(classId, YearMonth.of(year, month), threshold);
    }
}
