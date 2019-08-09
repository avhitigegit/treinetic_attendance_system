package com.attend.demo.controller;

import com.attend.demo.config.JwtTokenUtil;
import com.attend.demo.model.Employee;
import com.attend.demo.model.JwtRequest;
import com.attend.demo.service.LoginService;
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
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Employee> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse response) throws Exception {
        final Employee employee = new Employee();
        BeanUtils.copyProperties(loginService.loadEmployeeByLogin(authenticationRequest), employee);
        final String token = jwtTokenUtil.generateToken(employee);
        response.addHeader("Authorization", token);
        return ResponseEntity.ok(employee);
    }

//    final Employee employee = new Employee();
//        BeanUtils.copyProperties(loginService.loadEmployeeByLogin(authenticationRequest), employee);
//        CurrentEmployee.setEmployee(employee);
//        return ResponseEntity.ok(employee);

//    return ResponseEntity.ok(new JwtResponse(token));
}
