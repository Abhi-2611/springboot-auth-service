package com.example.app.backend.school.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseDao {

    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean last;

    List<StudentDao> StudentList;
    List<TeacherDao> TeacherList;
}
