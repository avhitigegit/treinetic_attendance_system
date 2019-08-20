package com.attend.demo.service;

import com.attend.demo.config.JwtTokenUtil;
import com.attend.demo.dto.EmployeeDto;
import com.attend.demo.exception.EmployeeAlreadyExistException;
import com.attend.demo.model.Employee;
import com.attend.demo.repository.EmployeeRepository;
import com.attend.demo.service.utill.EmployeeUtillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmplyeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeUtillService employeeUtillService;
    @Autowired
    EmailService emailService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    RoleService roleService;

    //Generate A New Employee
    public String createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        LocalDateTime now = LocalDateTime.now();
        String token = null;

        try {
            if (employeeDto != null && employeeUtillService.isValidEmail(employeeDto.getEmail())
                    && employeeUtillService.employeeIsAlreadyExist(employeeDto.getEmployeeId()) == false) {
                //Password Hashed
                employeeDto.setPassword(employeeUtillService.bCryptPassword(employeeDto.getPassword()));
                employeeDto.setCreatedAt(now);
                employeeDto.setUpdatedAt(null);
                //Send Email With Random Pin
                Integer genratedPin = employeeUtillService.generatePin();
                emailService.sendMail(employeeDto.getEmail(), genratedPin);
                //Save the employee to database
                BeanUtils.copyProperties(employeeDto, employee);
                employeeRepository.save(employee);
                token = jwtTokenUtil.generateTokenForUserPin(genratedPin.toString(), employeeDto.getEmail());
            }
        } catch (EmployeeAlreadyExistException e) {
            e.printStackTrace();
        }
        return token;
    }

    //User Enter 4 Digit Generated Number.And if valid Send it to admin
    public Boolean getEmailVerificationCode(String pinFromUser, String token) {
        LocalDateTime now = LocalDateTime.now();
        Boolean status = null;
        List<String> userNPinList = jwtTokenUtil.getUserNPinFromToken(token);
        String userEmailOfToken = userNPinList.get(0);
        String genratedPinOfToken = userNPinList.get(1);
        Employee employeeOfToken = employeeRepository.findEmployeeByEmail(userEmailOfToken);
        //Checking Both Pins Are Ok
        if (employeeUtillService.matchingEmailGeneratedPin(pinFromUser, genratedPinOfToken) == true) {
            //Send employee object to Admin to Set the emailStatus true
            if (employeeOfToken != null) {
                employeeOfToken.setEmailStatus("TRUE");
                status = true;
            }
        } else {
            employeeOfToken.setEmailStatus("FALSE");
            status = false;
        }
        employeeOfToken.setUpdatedAt(now);
        employeeRepository.save(employeeOfToken);
        return status;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    public Employee findEmployeeById(String empid) {
        return employeeRepository.findEmployeeById(empid);
    }

    public String passwordResetRequest(String email) {
        //validate the user
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        String token = null;
        //if user valide,
        if (employee != null) {
            //generate a pin
            Integer genratedPin = employeeUtillService.generatePin();
            //send the email to use with the generated code
            emailService.sendMail(email, genratedPin);
            //generate token from pin and employeeid
            token = jwtTokenUtil.generateTokenForUserPin(genratedPin.toString(), employee.getId());
        }
        //send the email to user with the pin
        return token;
    }

    //User Enter 4 Digit Generated Number.And if valid
    //Save the new Password
    public Boolean getPinForpasswordResetNSaveNewPassword(String pinFromUser, String token, String password) {
        LocalDateTime now = LocalDateTime.now();
        Boolean status = null;
        List<String> userNPinList = jwtTokenUtil.getUserNPinFromToken(token);
        String user = userNPinList.get(0);
        String genratedPin = userNPinList.get(1);
        Employee employeeOfToken = employeeRepository.findByIdEmp(user);
        //Checking Both Pins Are Ok
        if (employeeUtillService.matchingEmailGeneratedPin(pinFromUser, genratedPin) && employeeOfToken != null) {
            //Send employee object to Admin to Set the emailStatus true
            status = true;
            employeeOfToken.setPassword(employeeUtillService.bCryptPassword(password));
            employeeOfToken.setUpdatedAt(now);
            employeeRepository.save(employeeOfToken);
        } else {
            status = false;
        }
        return status;
    }
}
