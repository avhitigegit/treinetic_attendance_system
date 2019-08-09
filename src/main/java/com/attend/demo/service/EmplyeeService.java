package com.attend.demo.service;

import com.attend.demo.dto.EmployeeDto;
import com.attend.demo.exception.EmployeeAlreadyExistException;
import com.attend.demo.model.Employee;
import com.attend.demo.repository.EmployeeRepository;
import com.attend.demo.service.utill.EmployeeUtillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
public class EmplyeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeUtillService employeeUtillService;
    @Autowired
    EmailService emailService;


    //Generate A New Employee
    public Employee createEmployee(EmployeeDto employeeDto) throws MessagingException, IOException {
        Employee employee = new Employee();

        try {
            if (employeeDto != null && employeeUtillService.isValidEmail(employeeDto.getEmail())
                    && employeeUtillService.employeeIsAlreadyExist(employeeDto.getEmployeeId()) == false) {
                //Password Hashed
                employeeDto.setPassword(employeeUtillService.bCryptPassword(employeeDto.getPassword()));

                //Send Email With Random Pin
                //String genratedPin = emailService.sendMail(employeeDto.getEmail());

                //Checking Both Pins Are Ok
//                String userPin = null; //get the pin from user
//                if(employeeUtillService.matchingEmailGeneratedPin(userPin,genratedPin) == true){
//                    //Send employee object to Admin to Set the emailStatus true
//
//                }

                //Save the employee to database
                BeanUtils.copyProperties(employeeDto, employee);
                employee = employeeRepository.save(employee);
            }
        } catch (EmployeeAlreadyExistException e) {
            e.printStackTrace();
        }
        return employee;
    }

    //User Enter 4 Digit Generated Number.And if valid Send it to admin
    public void enterGeneratedPin(String userPin) {
        String genratedPin = "emailService.sendMail(employeeDto.getEmail());";
        //Checking Both Pins Are Ok
        if (employeeUtillService.matchingEmailGeneratedPin(userPin, genratedPin) == true) {
            //Send employee object to Admin to Set the emailStatus true

        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Employee findEmployeeById(String empid) {
        return employeeRepository.findEmployeeById(empid);
    }


    public Employee updateEmployee(String id, Employee employee) {
        Employee employeeObj = employeeRepository.findEmployeeById(id);

        employeeObj.setFullName(employee.getFullName());
        employeeObj.setEmail(employee.getEmail());
        employeeObj.setContact(employee.getContact());
        employeeObj.setUpdatedAt(employee.getUpdatedAt());
        employeeObj.setImage(employee.getImage());

        Employee updatedEmployee = null;

        if (updatedEmployee == null) {

            updatedEmployee = employeeRepository.save(employeeObj);

        }

        return updatedEmployee;
    }

    //    @Override
    public void deleteEmployee(String id) {
        Employee employeeObj = employeeRepository.findEmployeeById(id);
        employeeRepository.delete(employeeObj);
    }
}
