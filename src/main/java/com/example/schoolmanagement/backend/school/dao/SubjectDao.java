package com.example.schoolmanagement.backend.school.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SubjectDao {
    
    private Long id;              
    private String subjectCode;
    private String subjectName;
    private String description;
    private Character activeFlag;

}
