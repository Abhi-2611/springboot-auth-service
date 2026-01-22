package com.example.app.backend.school.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.common.exception.DuplicateResourceException;
import com.example.app.backend.common.exception.ResourceNotFoundException;
import com.example.app.backend.school.dao.SubjectDao;
import com.example.app.backend.school.entity.Subject;
import com.example.app.backend.school.repository.SubjectRepository;
import com.example.app.backend.school.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {
    
    @Autowired
    private SubjectRepository subjectRepository;


    @Override
    public MessageResponse createSubject(SubjectDao subjectDao) {

        Subject subject;
        if (subjectDao.getId() != null) {
            subject = subjectRepository.findById(subjectDao.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Subject not found with Id: " + subjectDao.getId()));
            BeanUtils.copyProperties(subjectDao, subject);
            subjectRepository.save(subject);
            return MessageResponse.builder().message("Subject updated successfully").build();
        } else {
            String subjectCode = subjectDao.getSubjectCode().toUpperCase();
            if (subjectRepository.existsBySubjectCode(subjectCode)) {
                throw new DuplicateResourceException("Subject already exists with code: " + subjectCode);
            }
            subject = new Subject();
            BeanUtils.copyProperties(subjectDao, subject);
            subject.setSubjectCode(subjectCode);
            subjectRepository.save(subject);
            return MessageResponse.builder().message("Subject added successfully").build();
        }
    }

}
