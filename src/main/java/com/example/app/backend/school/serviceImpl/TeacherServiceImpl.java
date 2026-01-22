package com.example.app.backend.school.serviceImpl;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.app.backend.auth.dao.MessageResponse;
import com.example.app.backend.auth.repository.UserRepository;
import com.example.app.backend.common.exception.BadRequestException;
import com.example.app.backend.common.exception.DuplicateResourceException;
import com.example.app.backend.common.exception.ResourceNotFoundException;
import com.example.app.backend.school.dao.ResponseDao;
import com.example.app.backend.school.dao.TeacherDao;
import com.example.app.backend.school.entity.Teacher;
import com.example.app.backend.school.repository.TeacherRepository;
import com.example.app.backend.school.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Teacher getTeacherByUserId(Long userId) {
        return teacherRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for userId: " + userId));
    }

    @Override
    public MessageResponse createTeacher(TeacherDao teacherDao) {
        
        if (teacherDao.getUserId() == null) {
            throw new BadRequestException("UserId is required");
        }
        userRepository.findById(teacherDao.getUserId()).orElseThrow(() -> 
            new ResourceNotFoundException("User not found with Id: " + teacherDao.getUserId()));
        Teacher teacher;
        if (teacherDao.getId() != null) {
            teacher = teacherRepository.findById(teacherDao.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Teacher not found with Id: " + teacherDao.getId()));
            BeanUtils.copyProperties(teacherDao, teacher);
        } else {
            if (teacherRepository.existsByUserId(teacherDao.getUserId())) {
                throw new DuplicateResourceException("Teacher already exists for this user");
            }
            teacher = new Teacher();
            BeanUtils.copyProperties(teacherDao, teacher);
            teacher.setUserId(teacherDao.getUserId());
            teacher.setJoinDate(LocalDate.now());
            if (teacher.getActiveFlag() == null) {
                teacher.setActiveFlag('Y');
            }
        }
        teacherRepository.save(teacher);
        return MessageResponse.builder()
            .message(teacherDao.getId() == null ? "Teacher added Successfully" : "Teacher updated successfully").build();
    }

    @Override
    public ResponseDao getAllTeachers(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        ResponseDao responseDao = new ResponseDao();
        if (pageNo != null && pageSize != null) {
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
            Page<Teacher> page = teacherRepository.findAll(pageable);
            responseDao.setTeacherList(page.getContent()
                .stream().map(this::teacherEntityToDao).collect(Collectors.toList()));
            responseDao.setPageNo(page.getNumber());
            responseDao.setPageSize(page.getSize());
            responseDao.setTotalElements(page.getTotalElements());
            responseDao.setTotalPages(page.getTotalPages());
            responseDao.setLast(page.isLast());
        } else {
            responseDao.setTeacherList(teacherRepository.findAll()
                .stream().map(this::teacherEntityToDao).collect(Collectors.toList()));
        }
        return responseDao;
    }


    private TeacherDao teacherEntityToDao(Teacher teacher) {
        TeacherDao teacherDao = new TeacherDao();
        BeanUtils.copyProperties(teacher, teacherDao);
        return teacherDao;
    }
    
}
