package com.attend.demo.controller;

import com.attend.demo.config.JwtTokenUtil;
import com.attend.demo.model.Employee;
import com.attend.demo.utils.CurrentEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class JWTAppendAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Employee employee = CurrentEmployee.getEmployee();
        if (employee == null) {
            return object;
        } else {
            final String token = jwtTokenUtil.generateToken(employee);
            serverHttpResponse.getHeaders().add("Authorization", token);
            return object;
        }
    }
}
