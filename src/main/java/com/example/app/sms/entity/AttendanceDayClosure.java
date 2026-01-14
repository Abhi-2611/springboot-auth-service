package com.example.app.sms.entity;

import java.time.LocalDate;

import com.example.app.common.audit.Auditable;

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
@Table(name = "attendance_day_closure", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"class_id", "attendance_date"})})
public class AttendanceDayClosure extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_id")
    private Long classId;

    @Column(name = "attendance_date")
    private LocalDate attendanceDate;

    @Column(name = "closed")
    private Boolean closed;
    
}

