package com.attend.demo.service.impl;

import com.attend.demo.service.EmailService;
import com.attend.demo.service.utill.EmployeeUtillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmployeeUtillService employeeUtillService;

    public String sendMail(String receiverAddress) throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("emailSender@gmail.com", "password");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("emailSender@gmail.com", false));

        //Pin Generate and send to User Email
        Integer generatedPin = employeeUtillService.generatePin();

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverAddress));
        msg.setSubject("Confirm The Email With Four Digit Code");
        //Set  generated number
        msg.setContent(generatedPin, "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.attachFile("/var/tmp/image19.png");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
        return generatedPin.toString();
    }

}
