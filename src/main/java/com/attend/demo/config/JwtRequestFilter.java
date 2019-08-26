package com.attend.demo.config;

import com.attend.demo.model.Employee;
import com.attend.demo.repository.EmployeeRepository;
import com.attend.demo.utils.CurrentEmployee;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        String email = null;
        String jwtToken = null;

        //When the time of first request to system
        //login and createEmployee
        if (requestTokenHeader == null) {
            chain.doFilter(request, response);
            return;
        }

        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        else if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                email = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }

        // Once we get the token validate it.
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Employee employee = this.employeeRepository.findEmployeeByEmail(email);
            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, employee)) {
                CurrentEmployee.setEmployee(employee);
                //In securitycontextholder set the authenticated current logged user.
                chain.doFilter(request, response);
            }
        }
    }
}
