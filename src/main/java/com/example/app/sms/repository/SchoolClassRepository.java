package com.example.app.sms.repository;

import com.example.app.sms.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

    Optional<SchoolClass> findByClassNameAndSectionAndAcademicYear(String className, 
        String section, String academicYear);
}
