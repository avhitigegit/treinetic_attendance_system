package com.attend.demo.exception;

public class EmployeeAlreadyExistException extends RuntimeException {
    //409 - resource already exist
    public EmployeeAlreadyExistException(String message) {
        super(message);
    }
}
