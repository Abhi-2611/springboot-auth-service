package com.example.app.backend.school.service;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.school.dao.ResponseDao;
import com.example.app.backend.school.dao.TeacherDao;
import com.example.app.backend.school.entity.Teacher;

public interface TeacherService {

    Teacher getTeacherByUserId(Long userId);

    MessageResponse createTeacher(TeacherDao teacherDao);

    ResponseDao getAllTeachers(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

}
