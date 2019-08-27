package com.attend.demo.controller;

import com.attend.demo.dto.ExceptionResponse;
import com.attend.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    LocalDateTime now = LocalDateTime.now();

    //    Token Exception
    @ExceptionHandler(AuthenticationTokenInvalidException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public @ResponseBody
    ExceptionResponse authenticationTokenInvalidException(final AuthenticationTokenInvalidException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorUrl(request.getRequestURI());
        error.setStatusCode(401);
        error.setTimestamp(now);
        return error;
    }

    //    Login Controller Exceptions
    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse authenticationFailedException(final AuthenticationFailedException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorUrl(request.getRequestURI());
        error.setStatusCode(401);
        error.setTimestamp(now);
        return error;
    }

    //Employee Controller Exceptions
    @ExceptionHandler(EmployeeAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody
    ExceptionResponse employeeAlreadyExistException(final EmployeeAlreadyExistException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorUrl(request.getRequestURI());
        error.setStatusCode(409);
        error.setTimestamp(now);
        return error;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse employeeNotFoundException(final EmployeeNotFoundException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorUrl(request.getRequestURI());
        error.setStatusCode(404);
        error.setTimestamp(now);
        return error;
    }

    @ExceptionHandler(EmailInvalidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse emailInvalidException(final EmailInvalidException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorUrl(request.getRequestURI());
        error.setStatusCode(400);
        error.setTimestamp(now);
        return error;
    }

    @ExceptionHandler(ClientRequestNotCompleteException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse clientRequestNotCompleteException(final ClientRequestNotCompleteException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorUrl(request.getRequestURI());
        error.setStatusCode(400);
        error.setTimestamp(now);
        return error;
    }

    @ExceptionHandler(EmailResponseNotMatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse emailResponseNotMatchException(final EmailResponseNotMatchException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorUrl(request.getRequestURI());
        error.setStatusCode(400);
        error.setTimestamp(now);
        return error;
    }


//    Attendance Controller Exceptions


//    Leave Controller Exceptions


    //    RabbitMQ Exception
    @ExceptionHandler(MessageNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse messageNotFoundException(final MessageNotFoundException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorUrl(request.getRequestURI());
        error.setStatusCode(404);
        error.setTimestamp(now);
        return error;
    }

    @ExceptionHandler(MessageNotSendException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse messageNotSendException(final MessageNotSendException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorUrl(request.getRequestURI());
        error.setStatusCode(404);
        error.setTimestamp(now);
        return error;
    }
}
