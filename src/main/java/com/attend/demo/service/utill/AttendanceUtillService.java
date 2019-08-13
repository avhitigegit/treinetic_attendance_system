package com.attend.demo.service.utill;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AttendanceUtillService {

    public boolean isValidDate(Date date) {
        return new Date().after(date);
    }

}
