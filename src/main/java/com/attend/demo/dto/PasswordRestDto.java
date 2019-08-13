package com.attend.demo.dto;

import java.io.Serializable;

public class PasswordRestDto implements Serializable {
    private static final long serialVersionUID = 4865903039190150223L;

    private String pinFromUser;
    private String token;
    private String password;

    public String getPinFromUser() {
        return pinFromUser;
    }

    public void setPinFromUser(String pinFromUser) {
        this.pinFromUser = pinFromUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
