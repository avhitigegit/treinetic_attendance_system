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

    //Generate A New Employee
    public Attendance createAttendance(AttendanceDto attendanceDto) {
        Attendance attendance = new Attendance();
        try {
            if (attendanceDto != null) {
                boolean validDate = attendanceUtillService.isValidDate(attendanceDto.getDate());
                //out time is must be greater than  in time
                if (attendanceDto.getTimeIn().getTime() < attendanceDto.getTimeOut().getTime()) {
                    Employee employee = CurrentEmployee.getEmployee();
                    Date now = new Date();
                    attendanceDto.setCreatedAt(now);
                    attendanceDto.setEmployee(employee);
                    BeanUtils.copyProperties(attendanceDto, attendance);
                    attendance = attendanceRepository.save(attendance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendance;
    }
}
