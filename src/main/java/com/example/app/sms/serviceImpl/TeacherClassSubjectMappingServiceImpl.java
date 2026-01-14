package com.example.app.sms.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.app.common.exception.BadRequestException;
import com.example.app.common.exception.DuplicateResourceException;
import com.example.app.common.exception.ResourceNotFoundException;
import com.example.app.rls.dao.MessageResponse;
import com.example.app.rls.entity.User;
import com.example.app.rls.repository.UserRepository;
import com.example.app.sms.dao.TeacherClassSubjectMappingDao;
import com.example.app.sms.entity.Subject;
import com.example.app.sms.entity.Teacher;
import com.example.app.sms.entity.TeacherClassSubjectMapping;
import com.example.app.sms.repository.SchoolClassRepository;
import com.example.app.sms.repository.SubjectRepository;
import com.example.app.sms.repository.TeacherClassSubjectMappingRepository;
import com.example.app.sms.repository.TeacherRepository;
import com.example.app.sms.service.TeacherClassSubjectMappingService;

@Service
public class TeacherClassSubjectMappingServiceImpl implements TeacherClassSubjectMappingService{

    @Autowired
    private TeacherClassSubjectMappingRepository teacherClassSubjectMappingRepository;

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public MessageResponse assignSubjectToTeacher(TeacherClassSubjectMappingDao teacherClassSubjectMappingDao) {
        
        if (teacherClassSubjectMappingDao.getTeacherId() == null ||
            teacherClassSubjectMappingDao.getClassId() == null ||
            teacherClassSubjectMappingDao.getSubjectId() == null) {
            throw new BadRequestException("TeacherId, ClassId and SubjectId are required");
        }

        teacherRepository.findById(teacherClassSubjectMappingDao.getTeacherId()).orElseThrow(() ->
            new ResourceNotFoundException("Teacher not found with Id: " + teacherClassSubjectMappingDao.getTeacherId()));

        schoolClassRepository.findById(teacherClassSubjectMappingDao.getClassId()).orElseThrow(() ->
            new ResourceNotFoundException("Class not found with Id: " + teacherClassSubjectMappingDao.getClassId()));

        Subject subject = subjectRepository.findById(teacherClassSubjectMappingDao.getSubjectId()).orElseThrow(() ->
            new ResourceNotFoundException("Subject not found with Id: " + teacherClassSubjectMappingDao.getSubjectId()));

        if (subject.getActiveFlag() == 'N') {
            throw new BadRequestException("Inactive subject cannot be assigned");
        }

        if (teacherClassSubjectMappingRepository.existsByTeacherIdAndClassIdAndSubjectId(teacherClassSubjectMappingDao.getTeacherId(), 
            teacherClassSubjectMappingDao.getClassId(), teacherClassSubjectMappingDao.getSubjectId())) {
            throw new DuplicateResourceException("Subject already assigned to this teacher for the class");
        }

        TeacherClassSubjectMapping mapping = new TeacherClassSubjectMapping();
        mapping.setTeacherId(teacherClassSubjectMappingDao.getTeacherId());
        mapping.setClassId(teacherClassSubjectMappingDao.getClassId());
        mapping.setSubjectId(teacherClassSubjectMappingDao.getSubjectId());

        teacherClassSubjectMappingRepository.save(mapping);

        return MessageResponse.builder().message("Subject assigned to teacher successfully").build();
        
    }

    @Override
    public List<TeacherClassSubjectMappingDao> getMySubjectsAndClasses() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() ->
            new ResourceNotFoundException("User not found"));
        Teacher teacher = teacherRepository.findByUserId(user.getId()).orElseThrow(() ->
            new ResourceNotFoundException("Teacher profile not found for user"));

        return teacherClassSubjectMappingRepository.findAllByTeacherId(teacher.getId()).stream()
        .map(mapping -> {
                TeacherClassSubjectMappingDao res = new TeacherClassSubjectMappingDao();
                res.setClassId(mapping.getClassId());
                res.setSubjectId(mapping.getSubjectId());
                return res;
        })
        .collect(Collectors.toList());
    }
    
}
