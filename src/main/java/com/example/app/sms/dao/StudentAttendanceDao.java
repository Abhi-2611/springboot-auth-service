package com.example.app.sms.dao;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentAttendanceDao {

    private Long id;
    private Long studentId;
    private Long classId;
    private LocalDate attendanceDate;
    private Character status;
    private Character activeFlag;

    private Integer totalDays;
    private Integer presentDays;
    private Double attendancePercentage;

    private Integer totalStudents;
    private Integer totalPresent;
    private Integer totalAbsent;
    
}