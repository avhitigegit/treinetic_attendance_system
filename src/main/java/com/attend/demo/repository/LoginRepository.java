package com.attend.demo.repository;

import com.attend.demo.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends MongoRepository<Employee, String> {

    @Query("{ 'email' : ?0 }")
    Employee findEmployeeByUserName(String email);
}
