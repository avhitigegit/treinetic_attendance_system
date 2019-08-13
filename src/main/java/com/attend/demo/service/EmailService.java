package com.attend.demo.service;

public interface EmailService {
    // Send a Email User With Generated Pin
    String sendMail(String receiverAddress, Integer generatedPin);
}
