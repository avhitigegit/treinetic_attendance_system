package com.attend.demo.repository;

import com.attend.demo.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    //    MongoDB JSON-based Query Methods and Field Restriction
    @Query("{ 'employeeId' : ?0 }")
    Employee findEmployeeById(String empid);

    @Query("{ 'email' : ?0 }")
    Employee findEmployeeByEmail(String email);

    @Query("{ 'id' : ?0 }")
    Employee findByIdEmp(String id);

}
