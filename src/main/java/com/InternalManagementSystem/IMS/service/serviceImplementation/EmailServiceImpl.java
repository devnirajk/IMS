package com.InternalManagementSystem.IMS.service.serviceImplementation;

import com.InternalManagementSystem.IMS.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    // To send a simple email
    public String sendSimpleMail(String to, String otp) {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(to);
            mailMessage.setText("Dear user, \n\n" +
                                "Your OTP is "  + otp  + ". This is valid for 10 minutes. \n\n Regards, IMS");
            mailMessage.setSubject("IMS");

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Otp Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail " + e.getMessage();
        }
    }
}
