package com.example.schoolmanagement.backend.school.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.schoolmanagement.backend.school.entity.AttendanceDayClosure;

public interface AttendanceDayClosureRepository extends JpaRepository<AttendanceDayClosure, Long> {

    Optional<AttendanceDayClosure> findByClassIdAndAttendanceDate(Long classId, LocalDate date);
    
}
