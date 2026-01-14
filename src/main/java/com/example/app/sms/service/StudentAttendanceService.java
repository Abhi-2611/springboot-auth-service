package com.example.app.sms.service;

import java.time.LocalDate;
import java.util.List;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.StudentAttendanceDao;

public interface StudentAttendanceService {

    MessageResponse markAttendance(StudentAttendanceDao studentAttendanceDao);

    List<StudentAttendanceDao> getClassAttendance(Long classId, LocalDate date);
    
}
