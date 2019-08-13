package com.attend.demo.controller;

import com.attend.demo.dto.EmployeeDto;
import com.attend.demo.dto.PasswordRestDto;
import com.attend.demo.dto.VerificationDto;
import com.attend.demo.model.Employee;
import com.attend.demo.service.EmplyeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmplyeeService emplyeeService;

    //Generate A New Employee And Return Token As A String To Response Body
    @ResponseBody
    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee, HttpServletResponse response) {
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto);
        String token = emplyeeService.createEmployee(employeeDto);
        return ResponseEntity.ok(token);
    }

    //Get The verification from generated empployee
    @ResponseBody
    @RequestMapping(value = "/getVerification", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Boolean> getEmailVerificationCode(@RequestBody VerificationDto verificationDto) {
        Boolean status = emplyeeService.getEmailVerificationCode(verificationDto.getPinFromUser(), verificationDto.getToken());
        return ResponseEntity.ok(status);
    }

    // Retrieve All Employees
    @ResponseBody
    @RequestMapping(value = "/allEmp", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = emplyeeService.getAllEmployees();
        return ResponseEntity.ok(employeeList);
    }

    // Retrieve A Employee By employee ID
    @ResponseBody
    @RequestMapping(value = "/{empid}", method = RequestMethod.GET)
    public ResponseEntity<Employee> findEmployeeById(@PathVariable(value = "empid") String empid) {
        Employee employee = emplyeeService.findEmployeeById(empid);
        return ResponseEntity.ok(employee);
    }

    // Password Reset Request From Email in URL
    @ResponseBody
    @RequestMapping(value = "pwd-reset/{email}", method = RequestMethod.GET)
    public ResponseEntity<String> passwordResetRequest(@PathVariable(value = "email") String email) {
        String token = emplyeeService.passwordResetRequest(email);
        return ResponseEntity.ok(token);
    }

    // Password Reset Request And Save New Password
    @ResponseBody
    @RequestMapping(value = "/pwd-reset/verification", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Boolean> getPinForpasswordResetNSaveNewPassword(@RequestBody PasswordRestDto passwordRestDto) {
        Boolean status = emplyeeService.getPinForpasswordResetNSaveNewPassword(passwordRestDto.getPinFromUser(), passwordRestDto.getToken(), passwordRestDto.getPassword());
        return ResponseEntity.ok(status);
    }
}
