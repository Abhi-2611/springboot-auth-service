package com.example.app.sms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.app.common.exception.BadRequestException;
import com.example.app.common.exception.DuplicateResourceException;
import com.example.app.common.exception.ResourceNotFoundException;
import com.example.app.rls.dao.MessageResponse;
import com.example.app.sms.dao.TeacherClassSubjectMappingDao;
import com.example.app.sms.entity.Subject;
import com.example.app.sms.entity.TeacherClassSubjectMapping;
import com.example.app.sms.repository.SchoolClassRepository;
import com.example.app.sms.repository.SubjectRepository;
import com.example.app.sms.repository.TeacherClassSubjectMappingRepository;
import com.example.app.sms.repository.TeacherRepository;
import com.example.app.sms.service.TeacherClassSubjectMappingService;

public class TeacherClassSubjectMappingServiceImpl implements TeacherClassSubjectMappingService{

    @Autowired
    private TeacherClassSubjectMappingRepository teacherClassSubjectMappingRepository;

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private SubjectRepository subjectRepository;

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
    
}
