package com.example.app.sms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.sms.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
    boolean existsBySubjectCode(String subjectCode);

    Optional<Subject> findBySubjectCode(String subjectCode);

}
