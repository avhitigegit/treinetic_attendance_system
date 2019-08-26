package com.attend.demo.config;

import com.attend.demo.model.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private static final long serialVersionUID = -2550185165626007488L;
    @Value("${jwt.secret}")
    private String secret;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims;
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user email
    public String generateToken(Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, employee.getEmail());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, Employee employee) {
        final String username = getUsernameFromToken(token);
        return (username.equals(employee.getEmail()) && !isTokenExpired(token));
    }

    //token generate at the time of employee registration
    public String generateTokenForUserPin(String generatedPin, String userEmail) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateTokenForGeneratedPin(claims, generatedPin, userEmail);
    }

    private String doGenerateTokenForGeneratedPin(Map<String, Object> claims, String generatedPin, String userEmail) {
        claims.put("useremail", userEmail);
        return Jwts.builder().setClaims(claims).setSubject(generatedPin).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //retrieve username and pin from token which generate at the time of employee registration
    public List<String> getUserNPinFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody();
        List<String> list = new ArrayList<>();
        String userEmail = claims.get("useremail", String.class);
        String generatedPin = getClaimFromToken(token, Claims::getSubject);
        list.add(userEmail);
        list.add(generatedPin);
        return list;
    }

}
