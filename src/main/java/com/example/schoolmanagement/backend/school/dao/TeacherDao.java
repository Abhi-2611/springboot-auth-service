package com.example.schoolmanagement.backend.school.dao;

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
    
    private Long id;
    private String employeeCode;
    private String firstName;
    private String lastName;
    private String qualification;
    private String designation;
    private LocalDate joinDate;
    private Character activeFlag;
    private Long userId;

}
