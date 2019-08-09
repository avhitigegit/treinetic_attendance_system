package com.attend.demo.service;

import com.attend.demo.dto.EmployeeDto;
import com.attend.demo.exception.EmployeeNotFoundException;
import com.attend.demo.model.Employee;
import com.attend.demo.model.JwtRequest;
import com.attend.demo.repository.EmployeeRepository;
import com.attend.demo.repository.LoginRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDto loadEmployeeByLogin(JwtRequest authenticationRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        EmployeeDto employeeDto = new EmployeeDto();

        Employee employee = employeeRepository.findEmployeeByEmail(authenticationRequest.getEmail());
        BeanUtils.copyProperties(employee, employeeDto);
        String hashedPassword = employeeDto.getPassword();
        try {
            if (passwordEncoder.matches(authenticationRequest.getPassword(), hashedPassword)) {
                return employeeDto;
            }
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
