package com.example.app.sms.service;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.StudentAttendanceDao;

public interface StudentAttendanceService {

    MessageResponse markAttendance(StudentAttendanceDao studentAttendanceDao);
    
}
