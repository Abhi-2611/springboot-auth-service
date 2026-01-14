package com.example.app.sms.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.app.common.exception.BadRequestException;
import com.example.app.common.exception.ResourceNotFoundException;
import com.example.app.rls.dao.MessageResponse;
import com.example.app.rls.entity.User;
import com.example.app.rls.repository.UserRepository;
import com.example.app.sms.dao.StudentAttendanceDao;
import com.example.app.sms.entity.Student;
import com.example.app.sms.entity.StudentAttendance;
import com.example.app.sms.entity.Teacher;
import com.example.app.sms.repository.AttendanceDayClosureRepository;
import com.example.app.sms.repository.StudentAttendanceRepository;
import com.example.app.sms.repository.StudentRepository;
import com.example.app.sms.repository.TeacherClassMappingRepository;
import com.example.app.sms.repository.TeacherRepository;
import com.example.app.sms.service.StudentAttendanceService;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {

    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;
    
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherClassMappingRepository teacherClassMappingRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AttendanceDayClosureRepository attendanceDayClosureRepository;


    @Override
    public MessageResponse markAttendance(StudentAttendanceDao studentAttendanceDao) {

        if (studentAttendanceDao.getStudentId() == null || studentAttendanceDao.getClassId() == null ||
            studentAttendanceDao.getAttendanceDate() == null || studentAttendanceDao.getStatus() == null) {
            throw new BadRequestException("All attendance fields are required");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() ->
            new ResourceNotFoundException("User not found"));
        Teacher teacher = teacherRepository.findByUserId(user.getId()).orElseThrow(() ->
            new ResourceNotFoundException("Teacher not found for logged-in user"));
        Student student = studentRepository.findById(studentAttendanceDao.getStudentId()).orElseThrow(() ->
            new ResourceNotFoundException("Student not found with Id: " + studentAttendanceDao.getStudentId()));
        if (!student.getClassId().equals(studentAttendanceDao.getClassId())) {
            throw new BadRequestException("Student does not belong to this class");
        }
        Boolean isAssigned = teacherClassMappingRepository
            .existsByTeacherIdAndClassId(teacher.getId(),studentAttendanceDao.getClassId());
        if (!isAssigned) {
            throw new BadRequestException("Teacher is not assigned to this class");
        }
        StudentAttendance studentAttendance = studentAttendanceRepository
            .findByStudentIdAndAttendanceDate(studentAttendanceDao.getStudentId(), studentAttendanceDao.getAttendanceDate())
            .orElseGet(StudentAttendance::new);
        studentAttendance.setStudentId(studentAttendanceDao.getStudentId());
        studentAttendance.setClassId(studentAttendanceDao.getClassId());
        studentAttendance.setAttendanceDate(studentAttendanceDao.getAttendanceDate());
        studentAttendance.setStatus(studentAttendanceDao.getStatus());

        // lock
        int lockDays = 0;
        if (isLocked(studentAttendanceDao.getAttendanceDate(), lockDays)) {
            throw new BadRequestException("Attendance is locked for this date");
        }

        // auto close 
        attendanceDayClosureRepository
        .findByClassIdAndAttendanceDate(studentAttendanceDao.getClassId(), studentAttendanceDao.getAttendanceDate())
        .ifPresent(closure -> {
            if (Boolean.TRUE.equals(closure.getClosed())) {
                throw new BadRequestException("Attendance is closed for this class and date");
            }
        });
        studentAttendanceRepository.save(studentAttendance);

        return MessageResponse.builder().message("Attendance marked successfully").build();

    }

    @Override
    public List<StudentAttendanceDao> getClassAttendance(Long classId, LocalDate date) {

        if (classId == null || date == null) {
            throw new BadRequestException("ClassId and date are required");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() ->
            new ResourceNotFoundException("User not found"));
        Teacher teacher = teacherRepository.findByUserId(user.getId()).orElseThrow(() ->
            new ResourceNotFoundException("Teacher not found for logged-in user"));

        Boolean isAssigned = teacherClassMappingRepository
            .existsByTeacherIdAndClassId(teacher.getId(), classId);

        if (!isAssigned) {
            throw new BadRequestException("Teacher is not assigned to this class");
        }

        return studentAttendanceRepository.findAllByClassIdAndAttendanceDate(classId, date).stream()
            .map(att -> {
                StudentAttendanceDao res = new StudentAttendanceDao();
                res.setStudentId(att.getStudentId());
                res.setStatus(att.getStatus());
                res.setAttendanceDate(att.getAttendanceDate());
                return res;
            })
            .toList();
    }

    @Override
    public MessageResponse correctAttendance(StudentAttendanceDao studentAttendanceDao) {

        if (studentAttendanceDao.getId() == null) {
            throw new BadRequestException("Attendance Id is required");
        }
        if (studentAttendanceDao.getStatus() == null) {
            throw new BadRequestException("Attendance status is required");
        }
        if (studentAttendanceDao.getStatus() != 'P' && studentAttendanceDao.getStatus() != 'A') {
            throw new BadRequestException("Invalid attendance status");
        }
        StudentAttendance studentAttendance = studentAttendanceRepository.findById(studentAttendanceDao.getId()).orElseThrow(() ->
            new ResourceNotFoundException("Attendance not found with Id: " + studentAttendanceDao.getId()));
        studentAttendance.setStatus(studentAttendanceDao.getStatus());
        studentAttendanceRepository.save(studentAttendance);

        return MessageResponse.builder().message("Attendance corrected successfully").build();
    }

    // Lock Attendance After a Date
    public static Boolean isLocked(LocalDate attendanceDate, int lockDays) {
        LocalDate allowedTill = LocalDate.now().minusDays(lockDays);
        return attendanceDate.isBefore(allowedTill);
    }

}
