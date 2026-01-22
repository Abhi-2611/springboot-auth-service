package com.example.schoolmanagement.backend.school.service;

import java.util.List;

import com.example.schoolmanagement.backend.auth.dao.MessageResponse;
import com.example.schoolmanagement.backend.school.dao.ResponseDao;
import com.example.schoolmanagement.backend.school.dao.StudentDao;
import com.example.schoolmanagement.backend.school.entity.Student;

public interface StudentService {
 
    List<Student> getStudentsForTeacher(Long teacherId);

    MessageResponse createStudent(StudentDao studentDao);

    List<Student> getStudentsByClassId(Long classId);

    ResponseDao getAllStudents(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

}
