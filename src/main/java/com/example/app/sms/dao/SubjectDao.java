package com.example.app.sms.dao;

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
