package com.example.app.sms.controller;

import com.example.app.sms.dao.SchoolClassDao;
import com.example.app.sms.entity.SchoolClass;
import com.example.app.sms.service.SchoolClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
