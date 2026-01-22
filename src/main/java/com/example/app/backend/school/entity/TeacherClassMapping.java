package com.example.app.backend.school.entity;

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
@Table(name = "teacher_class_mapping", schema = "school", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"teacher_id", "class_id"})})
public class TeacherClassMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "class_id")
    private Long classId;

    @Column(name = "is_class_teacher")
    private Boolean isClassTeacher;

    @Column(name = "assigned_date")
    private LocalDate assignedDate;

}
