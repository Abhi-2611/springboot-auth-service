package com.example.app.sms.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SchoolClassDao {

    private String className;      
    private String section;        
    private String academicYear;  
    private Character activeFlag;
    
}
