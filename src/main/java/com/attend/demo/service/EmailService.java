package com.attend.demo.service;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    // Send a Email User With Generated Pin
    String sendMail(String receiverAddress) throws MessagingException, IOException;


}
