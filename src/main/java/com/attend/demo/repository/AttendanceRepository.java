package com.attend.demo.repository;

import com.attend.demo.model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AttendanceRepository extends MongoRepository<Attendance, Integer> {
    @Query("{ 'approvalStatus' : ?0 }")
    Attendance findAllPENDINGattendance(String approvalStatus);
}
