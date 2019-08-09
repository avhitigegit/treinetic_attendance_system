package com.attend.demo.repository;

import com.attend.demo.model.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LeaveRepository extends MongoRepository<Leave, Integer> {
}
