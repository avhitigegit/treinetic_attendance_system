package com.attend.demo.service.impl;

import com.attend.demo.service.EmailService;
import com.attend.demo.service.utill.EmployeeUtillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
@Component
public class EmailServiceImpl implements EmailService {
    @Autowired
    EmployeeUtillService employeeUtillService;

    @Value("${senderEmail}")
    private String senderEmail;
    @Value("${password}")
    private String password;

    public String sendMail(String receiverAddress, Integer generatedPin) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(senderEmail, false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverAddress));
            msg.setSubject("Confirm The Email With Four Digit Code");
            //Set  generated number
            msg.setContent(generatedPin, "text/html");
            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedPin.toString();
    }

}
