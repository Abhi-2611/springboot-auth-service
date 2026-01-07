package com.example.app.sms.repository;

import com.example.app.sms.entity.TeacherClassMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherClassMappingRepository extends JpaRepository<TeacherClassMapping, Long> {

    @Query(" SELECT t.classId from TeacherClassMapping t where t.teacherId = :teacherId")
    List<Long> findClassIdsByTeacherId(Long teacherId);

    boolean existsByTeacherIdAndClassId(Long teacherId, Long classId);

}
