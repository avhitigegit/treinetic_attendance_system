package com.attend.demo.service;

import com.attend.demo.dto.AttendanceDto;
import com.attend.demo.dto.AttendanceHistoryDto;
import com.attend.demo.dto.RequestAttendanceDto;
import com.attend.demo.exception.AttendanceRecordAlreadyApprovedException;
import com.attend.demo.model.Attendance;
import com.attend.demo.model.Employee;
import com.attend.demo.repository.AttendanceRepository;
import com.attend.demo.service.utill.AttendanceUtillService;
import com.attend.demo.utils.CurrentEmployee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                // apply compareTo()
                int value = attendanceDto.getTimeOut().compareTo(attendanceDto.getTimeIn());
                if (value > 0 && attendanceDto.getApprovalStatus() == null) {
                    LocalDateTime now = LocalDateTime.now();
                    attendanceDto.setApprovalStatus("PENDING");
                    attendanceDto.setEmployeeId(employee);
                    attendanceDto.setCreatedAt(now);
                    attendanceDto.setUpdatedAt(null);
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

    //Get Admin Approved For Pending Attendance
    public List<Attendance> getAdminApprovedForPendingAttendance(String adminEmail) {
        List<Attendance> allPendingAttendance = getAllPendingAttendance();
        if (adminEmail != null && allPendingAttendance != null) {
            //send all pending result to admin mail
            for (Attendance item : allPendingAttendance) {
                LocalDateTime now = LocalDateTime.now();
                item.setApprovalStatus("APPROVED");
                item.setApprovedId(CurrentEmployee.getEmployee());
                item.setUpdatedAt(now);
                attendanceRepository.save(item);
            }
        }
        return allPendingAttendance;
    }

    //Set Approval Type For All Approved Attendance
    public List<Attendance> setApprovalTypeForApprovedAttendance(List<AttendanceDto> attendanceDtoList) {
        List<Attendance> attendanceList = new ArrayList<>();
        try {
            for (AttendanceDto item : attendanceDtoList) {
                Attendance attendance = new Attendance();
                if (item.getApprovedId() != null && !item.getApprovalStatus().equals("PENDING")) {
                    LocalDateTime now = LocalDateTime.now();
                    if (item.getApprovalStatus().equals("APPROVED")) {
                        item.setApprovalType("PRESENT");
                        item.setUpdatedAt(now);
                    } else if (item.getApprovalStatus().equals("REJECT")) {
                        item.setApprovalType("ABSENT");
                        item.setUpdatedAt(now);
                    }
                    BeanUtils.copyProperties(item, attendance);
                    attendance = attendanceRepository.save(attendance);
                    attendanceList.add(attendance);
                }
            }
        } catch (AttendanceRecordAlreadyApprovedException e) {
            throw new ResponseStatusException(
                    HttpStatus.ALREADY_REPORTED, "This Record has been already approved", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendanceList;
    }

    //Get All Pending Attendance
    public List<Attendance> getAllPendingAttendance() {
        List<Attendance> pending = null;
        pending = attendanceRepository.findAllApprovalStatusAttendance("PENDING");
        return pending;
    }

    //Get All Approved or Reject Attendance
    public List<Attendance> getAllApprovedOrRejectAttendance() {
        List<Attendance> approvedOrRejectAttendance = null;
        approvedOrRejectAttendance = attendanceRepository.findAllApprovalStatusAttendance("APPROVED");
        return approvedOrRejectAttendance;
    }

    //Get ALL previous Attendance Record of Current Employee
    public List<Attendance> getAllPreviousAttendance() {
        //get all attendance record
        List<Attendance> attendanceList = attendanceRepository.getPreviousAttendanceRecordByEmp(CurrentEmployee.getEmployee());
        //get the last record of current employee
        return attendanceList;
    }

    //Get the last Attendance Record of Current Employee
    public Attendance getPreviousAttendanceRecord() {
        //get all attendance record
        List<Attendance> attendanceList = getAllPreviousAttendance();
        //get the last record of current employee
        return attendanceList.get(attendanceList.size() - 1);
    }

    //Get the Attendance Record of Current Employee which It Request
    public Attendance getAttendanceRecordItRequest(RequestAttendanceDto requestAttendanceDto) {
        Attendance attendance = new Attendance();
        //get all attendance record
        List<Attendance> attendanceList = getAllPreviousAttendance();
        //get the selected attendance record
        for (Attendance item : attendanceList) {
            Date date = item.getDate();
            LocalTime timeIn = item.getTimeIn();
            LocalTime timeOut = item.getTimeOut();
            if (date == requestAttendanceDto.getDate()
                    && timeIn == requestAttendanceDto.getTimeIn()
                    && timeOut == requestAttendanceDto.getTimeOut()) {
                attendance = item;
            }
        }
        return attendance;
    }

    //Update the Attendance record which not approved
    public Attendance updateAttendanceRecord(AttendanceDto attendanceDto) {
        Attendance newAttendance = new Attendance();
        Attendance previousAttendanceRecord = getPreviousAttendanceRecord();
        if (previousAttendanceRecord.getApprovalStatus().equals("PENDING")) {
            LocalDateTime now = LocalDateTime.now();
            previousAttendanceRecord.setApprovalStatus("DELETED");
            previousAttendanceRecord.setUpdatedAt(now);
            attendanceRepository.save(previousAttendanceRecord);
            newAttendance = createAttendance(attendanceDto);
        }
        return newAttendance;
    }


    //Delete the Attendance record which not Approved
    public void deleteTheAttendance(String attendanceId) {
//        int i = Integer.parseInt(attendanceId);
        Attendance attendance = attendanceRepository.getAttendanceRecordById(attendanceId);
        if (attendance.getApprovalStatus().equals("PENDING")) {
            attendanceRepository.delete(attendance);
        }
    }

    //Get the Current Employee Attendance History
    public List<Attendance> getAttendanceHistory(AttendanceHistoryDto attendanceHistoryDto) {
        List<Attendance> attendanceHistoryList = new ArrayList();
        List<Attendance> attendanceList = attendanceRepository.
                getAttendanceHistoryFromTo(attendanceHistoryDto.getFrom(), attendanceHistoryDto.getTo());

        for (Attendance item : attendanceList) {
            if (CurrentEmployee.getEmployee().getEmail().equals(item.getEmployeeId().getEmail())) {
                attendanceHistoryList.add(item);
            }
        }
        return attendanceHistoryList;
    }

}
