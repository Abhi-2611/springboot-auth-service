package com.example.app.backend.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.app.backend.school.entity.TeacherClassMapping;

import java.util.List;

@Repository
public interface TeacherClassMappingRepository extends JpaRepository<TeacherClassMapping, Long> {

    @Query(" SELECT t.classId from TeacherClassMapping t where t.teacherId = :teacherId")
    List<Long> findClassIdsByTeacherId(Long teacherId);

    Boolean existsByTeacherIdAndClassId(Long teacherId, Long classId);

}
