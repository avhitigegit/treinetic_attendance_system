package com.attend.demo.controller;

import com.attend.demo.dto.EmployeeDto;
import com.attend.demo.exception.AuthenticationFailedException;
import com.attend.demo.model.Employee;
import com.attend.demo.model.JwtRequest;
import com.attend.demo.service.LoginService;
import com.attend.demo.utils.CurrentEmployee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class JwtAuthenticationController {
    @Autowired
    private LoginService loginService;

    //Login Controller.
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Employee> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse response) {
        final Employee employee = new Employee();
        EmployeeDto employeeDto = loginService.loadEmployeeByLogin(authenticationRequest);
        if (employeeDto != null) {
            BeanUtils.copyProperties(employeeDto, employee);
            CurrentEmployee.setEmployee(employee);
            return ResponseEntity.ok(employee);
        } else {
            throw new AuthenticationFailedException("Authentication Failed. Should Not Repeat The Request Without Modification Exception.");
        }
    }
}