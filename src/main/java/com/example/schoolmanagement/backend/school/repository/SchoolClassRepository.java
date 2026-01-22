package com.example.schoolmanagement.backend.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.schoolmanagement.backend.school.entity.SchoolClass;

import java.util.Optional;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

    Optional<SchoolClass> findByClassNameAndSectionAndAcademicYear(String className, 
        String section, String academicYear);
}
