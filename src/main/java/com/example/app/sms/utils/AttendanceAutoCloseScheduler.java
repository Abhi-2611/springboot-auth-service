package com.example.app.sms.utils;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.app.sms.entity.AttendanceDayClosure;
import com.example.app.sms.repository.AttendanceDayClosureRepository;

@Component
public class AttendanceAutoCloseScheduler {

    @Autowired
    private AttendanceDayClosureRepository attendanceDayClosureRepository;

    @Scheduled(cron = "0 5 0 * * ?") // daily at 12:05 AM
    public void closePreviousDayAttendance() {

        LocalDate yesterday = LocalDate.now().minusDays(1);

        List<AttendanceDayClosure> openClosures = attendanceDayClosureRepository.findAll().stream()
        .filter(c -> c.getAttendanceDate().equals(yesterday) && !Boolean.TRUE.equals(c.getClosed())).toList();

        for (AttendanceDayClosure closure : openClosures) {
            closure.setClosed(true);
            attendanceDayClosureRepository.save(closure);
        }
    }
}

