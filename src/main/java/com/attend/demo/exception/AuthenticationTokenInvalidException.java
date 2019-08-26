package com.attend.demo.exception;

public class AuthenticationTokenInvalidException extends RuntimeException {
    public AuthenticationTokenInvalidException(String message) {
        super(message);
    }
}
