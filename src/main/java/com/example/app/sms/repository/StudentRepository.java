package com.example.app.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.sms.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByClassIdIn(List<Long> classIds);

    Student findByStudentCode(String studentCode);

    List<Student> findByClassId(Long classId);
    
}

