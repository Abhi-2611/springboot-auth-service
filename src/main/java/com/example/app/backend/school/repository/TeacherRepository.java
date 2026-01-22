package com.example.app.backend.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.backend.school.entity.Teacher;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByUserId(Long userId);

    Boolean existsByUserId (Long UserId);

    Page<Teacher> findAll(Pageable pageable);

}
