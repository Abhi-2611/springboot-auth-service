package com.example.schoolmanagement.backend.school.entity;

import java.time.LocalDate;

import com.example.schoolmanagement.backend.common.audit.Auditable;

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
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "teachers", schema = "school", uniqueConstraints = {@UniqueConstraint(columnNames = "employee_code")})
public class Teacher extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_code")
    private String employeeCode;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "designation")
    private String designation;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(name = "active_flag")
    private Character activeFlag;

    @Column(name = "user_id")
    private Long userId;
    
}
