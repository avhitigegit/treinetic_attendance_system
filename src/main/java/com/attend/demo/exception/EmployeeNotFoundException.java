package com.attend.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {
    //404 - resource not found
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
