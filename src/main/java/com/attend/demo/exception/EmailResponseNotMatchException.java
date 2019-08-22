package com.attend.demo.exception;

public class EmailResponseNotMatchException extends RuntimeException {
    public EmailResponseNotMatchException(String message) {
        super(message);
    }
}
