package com.attend.demo.service.utill;

import com.attend.demo.exception.ClientRequestNotCompleteException;
import com.attend.demo.exception.EmailInvalidException;
import com.attend.demo.exception.EmailResponseNotMatchException;
import com.attend.demo.exception.EmployeeAlreadyExistException;
import com.attend.demo.model.Employee;
import com.attend.demo.repository.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmployeeUtillService {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeUtillService.class);

    @Autowired
    EmployeeRepository employeeRepository;

    //Email Validate
    public boolean isValidEmail(String email) {
        Boolean staus;
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (email.matches(regex)) {
            staus = true;
        } else {
            throw new EmailInvalidException("Invalid Email Addresses Exception.");
        }
        return staus;
    }

    //Check Employee Already Exist
    public boolean employeeIsAlreadyExist(String employeeId) {
        Boolean exist;
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if (employee != null) {
            throw new EmployeeAlreadyExistException("Employee Already Exist Exception.");
        } else {
            exist = false;
        }
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
        Boolean status;
        status = userPin.equalsIgnoreCase(generatedPin);
        if (status == true) {
            return status;
        } else {
            throw new EmailResponseNotMatchException("Invalid Pin Exception.");
        }
    }

    //Create the Password
    public String bCryptPassword(String password) {
        if (password != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            return hashedPassword;
        } else {
            throw new ClientRequestNotCompleteException("Does Not Pass The Correct Password Exception.");
        }
    }
}
