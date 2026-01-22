package com.example.app.backend.school.dao;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherClassMappingDao {
    
    private Long teacherId;
    private Long classId;
    private Boolean isClassTeacher;
    private LocalDate assignedDate;

}
