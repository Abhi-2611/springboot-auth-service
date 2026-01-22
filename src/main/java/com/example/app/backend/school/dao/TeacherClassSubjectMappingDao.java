package com.example.app.backend.school.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherClassSubjectMappingDao {

    private Long id;
    private Long teacherId;
    private Long classId;
    private Long subjectId;
    private Character activeFlag;

}
