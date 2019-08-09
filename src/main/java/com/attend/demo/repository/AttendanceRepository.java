package com.attend.demo.repository;

import com.attend.demo.model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttendanceRepository extends MongoRepository<Attendance, Integer> {
}
