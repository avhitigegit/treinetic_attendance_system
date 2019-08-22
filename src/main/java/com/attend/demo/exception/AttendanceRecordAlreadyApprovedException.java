package com.attend.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AttendanceRecordAlreadyApprovedException extends RuntimeException {
    //409 - resource already exist
    public AttendanceRecordAlreadyApprovedException() {
        super();
    }

    public AttendanceRecordAlreadyApprovedException(String message) {
        super(message);
    }
}
