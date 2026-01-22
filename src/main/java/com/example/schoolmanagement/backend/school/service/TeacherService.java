package com.example.schoolmanagement.backend.school.service;

import com.example.schoolmanagement.backend.auth.dao.MessageResponse;
import com.example.schoolmanagement.backend.school.dao.ResponseDao;
import com.example.schoolmanagement.backend.school.dao.TeacherDao;
import com.example.schoolmanagement.backend.school.entity.Teacher;

public interface TeacherService {

    Teacher getTeacherByUserId(Long userId);

    MessageResponse createTeacher(TeacherDao teacherDao);

    ResponseDao getAllTeachers(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

}
