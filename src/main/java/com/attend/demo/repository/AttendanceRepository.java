package com.attend.demo.repository;

import com.attend.demo.model.Attendance;
import com.attend.demo.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends MongoRepository<Attendance, Integer> {
//    @Query("{ 'date' : ?0, 'timeIn' : ?1, 'timeOut' : ?2, 'employee' : ?3 }")
//    Attendance getPreviousAttendanceRecord(String date, String timeIn, String timeOut, Employee employee);

    @Query("{ 'approvalStatus' : ?0 }")
    List<Attendance> findAllApprovalStatusAttendance(String approvalStatus);

    @Query("{ 'id' : ?0 }")
    Attendance getAttendanceRecordById(String id);

    //get all the attendance by employee
    @Query("{ 'employeeId' : ?0 }")
    List<Attendance> getPreviousAttendanceRecordByEmp(Employee employee);

    //Get the Current Employee Attendance History
//    @Query("{ 'employeeId' : ?0 }")
//    List<Attendance> getAttendanceHistory(Employee employee);

    @Query("{ 'date' : { $gt: ?0, $lt: ?1 } }")
    List<Attendance> getAttendanceHistoryFromTo(Date from, Date to);

}
