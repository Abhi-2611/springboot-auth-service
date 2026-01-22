package com.example.app.backend.school.entity;

import com.example.app.backend.common.audit.Auditable;

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
@Table(name = "subject", schema = "school", uniqueConstraints = {
    @UniqueConstraint(columnNames = "subject_code")})
public class Subject extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "subject_name")
    private String subjectName;

}
