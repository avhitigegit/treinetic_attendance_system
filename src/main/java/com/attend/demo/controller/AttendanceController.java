package com.attend.demo.controller;

import com.attend.demo.dto.AttendanceDto;
import com.attend.demo.dto.AttendanceHistoryDto;
import com.attend.demo.dto.RequestAttendanceDto;
import com.attend.demo.model.Attendance;
import com.attend.demo.service.AttendanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;

    //Generate A New Attendance
    @ResponseBody
    @RequestMapping(value = "/create-new-attendance", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        AttendanceDto attendanceDto = new AttendanceDto();
        BeanUtils.copyProperties(attendance, attendanceDto);
        attendance = attendanceService.createAttendance(attendanceDto);
        return ResponseEntity.ok(attendance);
    }

    //Set Approval Type For Approved All Attendance
    @ResponseBody
    @RequestMapping(value = "/approval-attendance", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Attendance>> setApprovalTypeForApprovedAttendance() {
        List<Attendance> attendanceList = attendanceService.getAllApprovedOrRejectAttendance();
        List<AttendanceDto> approvedAttendanceDtoList = new ArrayList<>();
        for (Attendance item : attendanceList) {
            AttendanceDto attendanceDto = new AttendanceDto();
            BeanUtils.copyProperties(item, attendanceDto);
            approvedAttendanceDtoList.add(attendanceDto);
        }
        attendanceList = attendanceService.setApprovalTypeForApprovedAttendance(approvedAttendanceDtoList);
        return ResponseEntity.ok(attendanceList);
    }

    //Get All Pending Attendance
    @ResponseBody
    @RequestMapping(value = "/all-pending-attendance", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Attendance>> getAllPendingAttendance() {
        List<Attendance> attendance = attendanceService.getAllPendingAttendance();
        return ResponseEntity.ok(attendance);
    }

    //Get the Attendance Record of Current Employee which It Request
    @ResponseBody
    @RequestMapping(value = "/previous-attendance", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Attendance> getAttendanceRecordItRequest(@RequestBody RequestAttendanceDto requestAttendanceDto) {
        Attendance attendance = attendanceService.getAttendanceRecordItRequest(requestAttendanceDto);
        return ResponseEntity.ok(attendance);
    }

    //Get the previous Attendance Record of Current Employee
    @ResponseBody
    @RequestMapping(value = "/previous-attendance", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Attendance> getPreviousAttendanceRecord() {
        Attendance attendance = attendanceService.getPreviousAttendanceRecord();
        return ResponseEntity.ok(attendance);
    }

    //Update the previous Attendance Record
    @ResponseBody
    @RequestMapping(value = "/update-attendance", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Attendance> updateAttendanceRecord(@RequestBody Attendance attendance) {
        AttendanceDto attendanceDto = new AttendanceDto();
        BeanUtils.copyProperties(attendance, attendanceDto);
        attendance = attendanceService.updateAttendanceRecord(attendanceDto);
        return ResponseEntity.ok(attendance);
    }

    //Delete the Attendance Record Which not Approved
    @ResponseBody
    @RequestMapping(value = "/delete-attendance/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteTheAttendance(@PathVariable(value = "id") String attendanceId) {
        attendanceService.deleteTheAttendance(attendanceId);
    }

    //Get the Current Employee Attendance History
    @ResponseBody
    @RequestMapping(value = "/history-attendance", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<List<Attendance>> getAttendanceHistory(@RequestBody AttendanceHistoryDto attendanceHistoryDto) {
        List<Attendance> attendanceHistoryList = attendanceService.getAttendanceHistory(attendanceHistoryDto);
        return ResponseEntity.ok(attendanceHistoryList);
    }
}
