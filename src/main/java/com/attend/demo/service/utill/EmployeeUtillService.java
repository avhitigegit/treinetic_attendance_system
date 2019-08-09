package com.attend.demo.service.utill;

import com.attend.demo.model.Employee;
import com.attend.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmployeeUtillService {

    @Autowired
    EmployeeRepository employeeRepository;

    //Email Validate
    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    //Check Employee Already Exist
    public boolean employeeIsAlreadyExist(String employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        Boolean exist = null;
        exist = employee != null;
        return exist;
    }

    //Generate Four Digit Code
    public Integer generatePin() {
        Random rand = new Random();
        Integer generatedPin = rand.nextInt(10000);
        return generatedPin;
    }

    //Checking pins are matching
    public Boolean matchingEmailGeneratedPin(String userPin, String generatedPin) {
        Boolean status = null;
        status = userPin.equalsIgnoreCase(generatedPin);
        return status;
    }

    //Create the Password
    public String bCryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    //When Login
    //passwordEncoder.matches(senderPassword,hashedPasswordFromDB);

}
