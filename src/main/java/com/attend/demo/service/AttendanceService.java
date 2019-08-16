package com.attend.demo.service;

import com.attend.demo.dto.AttendanceDto;
import com.attend.demo.model.Attendance;
import com.attend.demo.model.Employee;
import com.attend.demo.repository.AttendanceRepository;
import com.attend.demo.service.utill.AttendanceUtillService;
import com.attend.demo.utils.CurrentEmployee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    AttendanceUtillService attendanceUtillService;

    //Generate A New Attendance
    public Attendance createAttendance(AttendanceDto attendanceDto) {
        Attendance attendance = new Attendance();
        Employee employee = CurrentEmployee.getEmployee();
        try {
            if (attendanceDto != null && attendanceUtillService.isValidDate(attendanceDto.getDate()) == true) {
//                boolean validDate = attendanceUtillService.isValidDate(attendanceDto.getDate());
                //out time is must be greater than  in time
                if (attendanceDto.getTimeIn().getTime() < attendanceDto.getTimeOut().getTime() && attendanceDto.getApprovalStatus() == null) {
                    Date now = new Date();
                    attendanceDto.setCreatedAt(now);
                    attendanceDto.setApprovalStatus("PENDING");
                    attendanceDto.setEmployeeId(employee);
                    attendanceDto.setApprovalType(null);
                    attendanceDto.setApprovedId(null);
                    BeanUtils.copyProperties(attendanceDto, attendance);
                    attendance = attendanceRepository.save(attendance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendance;
    }

    //at now
    //set all the approvalStatus,approvalType,approvedId
    //Approval set Attendance
    public Attendance approvalAttendance(AttendanceDto attendanceDto) {
        Attendance attendance = new Attendance();
        try {
            if (attendanceDto.getApprovedId() != null && !attendanceDto.getApprovalStatus().equals("PENDING")) {
                if (attendanceDto.getApprovalStatus().equals("APPROVED")) {
                    attendanceDto.setApprovalType("PRESENT");
                } else if (attendanceDto.getApprovalStatus().equals("REJECT")) {
                    attendanceDto.setApprovalType("ABSENT");
                }
                BeanUtils.copyProperties(attendanceDto, attendance);
                attendance = attendanceRepository.save(attendance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendance;
    }

    //Send All Pending Attendance To Admin
    public Attendance sendAllPendingAttendanceToAdmin(String adminEmail) {
        Attendance pending = null;
        if (adminEmail != null) {
            pending = attendanceRepository.findAllPENDINGattendance("PENDING");
        }
        return pending;
    }
}
