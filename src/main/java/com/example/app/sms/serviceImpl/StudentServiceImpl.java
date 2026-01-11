package com.example.app.sms.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.app.common.exception.BadRequestException;
import com.example.app.common.exception.ResourceNotFoundException;
import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.ResponseDao;
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
        
        if (studentDao.getClassId() == null) {
            throw new BadRequestException("ClassId is required");
        }
        schoolClassRepository.findById(studentDao.getClassId()).orElseThrow(() ->
            new ResourceNotFoundException("Class not found with Id: " + studentDao.getClassId()));
        
        Student student;
        if (studentDao.getId() != null) {
            student = studentRepository.findById(studentDao.getId()).orElseThrow(() -> 
                new ResourceNotFoundException("Student not found with Id: " + studentDao.getId()));
            BeanUtils.copyProperties(studentDao, student);
        } else {
            student = new Student();
            BeanUtils.copyProperties(studentDao, student);
            student.setAdmissionDate(LocalDate.now());
            if (student.getActiveFlag() == null) {
                student.setActiveFlag('Y');
            }
        }
        studentRepository.save(student);

        return MessageResponse.builder()
            .message(studentDao.getId() == null ? "Student added successfully" : "Student updated successfully").build();
    }


    @Override
    public List<Student> getStudentsByClassId(Long classId) {
        schoolClassRepository.findById(classId)
            .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + classId));
        return studentRepository.findByClassId(classId);
    }

    @Override
    public ResponseDao getAllStudents(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        ResponseDao responseDao = new ResponseDao();
        if (pageNo != null && pageSize != null) {
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
            Page<Student> page = studentRepository.findAll(pageable);
            responseDao.setStudentList(page.getContent()
                .stream().map(this::studentEntityToDao).collect(Collectors.toList()));
            responseDao.setPageNo(page.getNumber());
            responseDao.setPageSize(page.getSize());
            responseDao.setTotalElements(page.getTotalElements());
            responseDao.setTotalPages(page.getTotalPages());
            responseDao.setLast(page.isLast());
        } else {
            responseDao.setStudentList(studentRepository.findAll()
                .stream().map(this::studentEntityToDao).collect(Collectors.toList()));
        }
        return responseDao;
    }


    private StudentDao studentEntityToDao(Student student) {
        StudentDao studentDao = new StudentDao();
        BeanUtils.copyProperties(student, studentDao);
        return studentDao;
    }
    
}
