package com.attend.demo.controller;

import com.attend.demo.dto.AttendanceDto;
import com.attend.demo.model.Attendance;
import com.attend.demo.service.AttendanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;

    //Generate A New Attendance
    @ResponseBody
    @RequestMapping(value = "/create-new-attend", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        AttendanceDto attendanceDto = new AttendanceDto();
        BeanUtils.copyProperties(attendance, attendanceDto);
        attendance = attendanceService.createAttendance(attendanceDto);
        return ResponseEntity.ok(attendance);
    }
}
