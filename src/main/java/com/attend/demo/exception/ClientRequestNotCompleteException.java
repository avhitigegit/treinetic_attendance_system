package com.attend.demo.exception;

public class ClientRequestNotCompleteException extends RuntimeException {
    //    400 - bad request
    public ClientRequestNotCompleteException(String message) {
        super(message);
    }
}
