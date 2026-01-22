package com.example.app.backend.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.app.backend.school.dao.SchoolClassDao;
import com.example.app.backend.school.entity.SchoolClass;
import com.example.app.backend.school.service.SchoolClassService;

@RestController
@RequestMapping("/api/admin/classes")
public class AdminClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @PostMapping("/createClass")
    @PreAuthorize("hasAnyRole('ADMIN','PRINCIPAL')")
    public SchoolClass createClass(@RequestBody  SchoolClassDao schoolClassDao) {
        return schoolClassService.createClass(schoolClassDao);
    }

}
