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
@Table (name = "teacher_class_subject_map", schema = "school", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"teacher_id", "class_id", "subject_id"})})
public class TeacherClassSubjectMapping extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "class_id")
    private Long classId;

    @Column(name = "subject_id")
    private Long subjectId;

}
