package com.attend.demo.controller;

import com.attend.demo.dto.EmployeeDto;
import com.attend.demo.model.Employee;
import com.attend.demo.service.EmplyeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmplyeeService emplyeeService;

    //Generate A New Employee
    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws MessagingException, IOException {
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto);
        Employee employeeObj = emplyeeService.createEmployee(employeeDto);
        return ResponseEntity.ok(employeeObj);
    }

    // Retrieve All Plans By planID
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getEmployee() {
        List<Employee> employee = emplyeeService.getAllEmployees();
        return employee;
    }
}
