package com.example.schoolmanagement.backend.school.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.schoolmanagement.backend.school.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByClassIdIn(List<Long> classIds);

    Student findByStudentCode(String studentCode);

    List<Student> findByClassId(Long classId);

    Page<Student> findAll(Pageable pageable);

    int countByClassId(Long classId);
    
}

