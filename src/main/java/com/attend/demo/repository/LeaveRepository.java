package com.attend.demo.repository;

import com.attend.demo.model.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends MongoRepository<Leave, String> {
}
