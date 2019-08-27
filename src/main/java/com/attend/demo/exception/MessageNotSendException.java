package com.attend.demo.exception;

public class MessageNotSendException extends RuntimeException {
    public MessageNotSendException(String message) {
        super(message);
    }
}
