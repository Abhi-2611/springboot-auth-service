package com.example.app.sms.service;

import java.util.List;

import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.ResponseDao;
import com.example.app.sms.dao.StudentDao;
import com.example.app.sms.entity.Student;

public interface StudentService {
 
    List<Student> getStudentsForTeacher(Long teacherId);

    MessageResponse createStudent(StudentDao studentDao);

    List<Student> getStudentsByClassId(Long classId);

    ResponseDao getAllStudents(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

}
