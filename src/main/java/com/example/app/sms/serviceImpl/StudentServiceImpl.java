package com.example.app.sms.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.common.exception.ResourceNotFoundException;
import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.StudentDao;
import com.example.app.sms.entity.Student;
import com.example.app.sms.repository.SchoolClassRepository;
import com.example.app.sms.repository.StudentRepository;
import com.example.app.sms.repository.TeacherClassMappingRepository;
import com.example.app.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherClassMappingRepository teacherClassMappingRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Override
    public List<Student> getStudentsForTeacher(Long teacherId) {
    
        List<Long> classIds = teacherClassMappingRepository.findClassIdsByTeacherId(teacherId);

        if (classIds.isEmpty()) {
            return List.of();
        }
        return studentRepository.findByClassIdIn(classIds);
    }


    @Override
    public MessageResponse createStudent(StudentDao studentDao) {
        
        schoolClassRepository.findById(studentDao.getClassId())
            .orElseThrow(() -> new ResourceNotFoundException("Class not found with Id: " + studentDao.getClassId()));

        Student student = new Student();
        student.setStudentCode(studentDao.getStudentCode());
        student.setFirstName(studentDao.getFirstName());
        student.setLastName(studentDao.getLastName());
        student.setRollNo(studentDao.getRollNo());
        student.setClassId(studentDao.getClassId());
        student.setSection(studentDao.getSection());
        student.setDateOfBirth(studentDao.getDateOfBirth());
        student.setAdmissionDate(LocalDate.now());
        student.setActiveFlag(studentDao.getActiveFlag());

        studentRepository.save(student);
        return MessageResponse.builder().message("Student added Successfully").build();
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    @Override
    public List<Student> getStudentsByClassId(Long classId) {
        schoolClassRepository.findById(classId)
            .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + classId));
        return studentRepository.findByClassId(classId);
    }
    
}
