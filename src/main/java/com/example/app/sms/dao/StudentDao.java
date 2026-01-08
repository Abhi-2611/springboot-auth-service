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
public class StudentDao {

    private String studentCode;
    private String firstName;
    private String lastName;
    private Integer rollNo;
    private Long classId;
    private String section;
    private LocalDate dateOfBirth;
    private LocalDate admissionDate;
    private Character activeFlag;
}
