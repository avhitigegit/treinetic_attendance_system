package com.attend.demo.controller;

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
    public ResponseEntity<Employee> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse response) throws Exception {
        final Employee employee = new Employee();
        BeanUtils.copyProperties(loginService.loadEmployeeByLogin(authenticationRequest), employee);
        CurrentEmployee.setEmployee(employee);
        return ResponseEntity.ok(employee);
    }
}
