package com.example.app.sms.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.sms.entity.StudentAttendance;

@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {

    Boolean existsByStudentIdAndAttendanceDate(Long studentId, LocalDate attendanceDate);

    List<StudentAttendance> findAllByClassIdAndAttendanceDate(Long classId, LocalDate attendanceDate);

    List<StudentAttendance> findAllByStudentId(Long studentId);

    Optional<StudentAttendance> findByStudentIdAndAttendanceDate(Long studentId, LocalDate attendanceDate);

    List<StudentAttendance> findAllByClassIdAndAttendanceDateBetween(Long classId, LocalDate startDate, LocalDate endDate);
    
}

