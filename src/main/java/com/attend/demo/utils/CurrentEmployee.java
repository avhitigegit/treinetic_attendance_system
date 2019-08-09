package com.attend.demo.utils;

import com.attend.demo.model.Employee;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CurrentEmployee {

    Employee employee;
    Map<String, String> extras;

    public CurrentEmployee(Employee employee, Map<String, String> extras) {
        this.employee = employee;
        this.extras = extras;
    }

    public static void setEmployee(Employee employee, Map<String, String> extras) {
        var currentEmployee = new CurrentEmployee(employee, extras);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(currentEmployee, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    public static Employee getEmployee() {
        var cEmployee = getCurrentEmployee();
        return cEmployee != null ? cEmployee.employee : null;
    }

    public static void setEmployee(Employee employee) {
        CurrentEmployee.setEmployee(employee, null);
    }

//    public static  Map<String, String> getExtras() {
//        var cEmployee = getCurrentEmployee();
//        return cEmployee != null ? cEmployee.extras : null;
//    }

    private static CurrentEmployee getCurrentEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof CurrentEmployee) {
            return (CurrentEmployee) authentication.getPrincipal();
        }
        return null;
    }


}
