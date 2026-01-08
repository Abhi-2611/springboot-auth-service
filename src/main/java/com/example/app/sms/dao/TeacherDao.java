package com.example.app.sms.dao;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherDao {
    
    private String employeeCode;
    private String firstName;
    private String lastName;
    private String qualification;
    private String designation;
    private LocalDate joinDate;
    private Character activeFlag;
    private Long userId;

}
