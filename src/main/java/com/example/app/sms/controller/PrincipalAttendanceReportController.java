package com.example.app.sms.controller;

import java.time.YearMonth;
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
}
