package com.attend.demo.dto;

import java.io.Serializable;

public class VerificationDto implements Serializable {
    private static final long serialVersionUID = 4865903039190150223L;

    private String pinFromUser;
    private String token;

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
}
