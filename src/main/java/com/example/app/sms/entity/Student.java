package com.example.app.sms.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "students", schema = "school", uniqueConstraints = {@UniqueConstraint(columnNames = "student_code")})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_code", unique = true)
    private String studentCode;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "roll_no")
    private Integer rollNo;

    @Column(name = "class_id")
    private Long classId;

    @Column(name = "section")
    private String section;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @Column(name = "active_flag")
    private Character activeFlag;
    
}

