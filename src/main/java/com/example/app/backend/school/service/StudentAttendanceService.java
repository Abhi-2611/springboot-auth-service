package com.example.app.backend.school.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.school.dao.StudentAttendanceDao;

public interface StudentAttendanceService {

    MessageResponse markAttendance(StudentAttendanceDao studentAttendanceDao);

    List<StudentAttendanceDao> getClassAttendance(Long classId, LocalDate date);

    MessageResponse correctAttendance(StudentAttendanceDao studentAttendanceDao);

    List<StudentAttendanceDao> getMonthlyReport(Long classId, YearMonth month);
    
    StudentAttendanceDao getClassMonthlyReport(Long classId, YearMonth month);

    List<StudentAttendanceDao> getLowAttendanceReport(Long classId, YearMonth month, Double thresholdPercentage);
    
    List<StudentAttendanceDao> getStudentAttendanceHistory(Long studentId, LocalDate fromDate, LocalDate toDate);
    
}
