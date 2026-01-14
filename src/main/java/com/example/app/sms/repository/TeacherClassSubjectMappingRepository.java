package com.example.app.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.sms.entity.TeacherClassSubjectMapping;

@Repository
public interface TeacherClassSubjectMappingRepository 
    extends JpaRepository<TeacherClassSubjectMapping, Long> {
    
    Boolean existsByTeacherIdAndClassIdAndSubjectId(Long teacherId, Long classId, Long subjectId);

    List<TeacherClassSubjectMapping> findAllByTeacherId(Long teacherId);

    List<TeacherClassSubjectMapping> findAllByActiveFlag(Character activeFlag);
    
}
