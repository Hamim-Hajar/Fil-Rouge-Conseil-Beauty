package com.example.conseil.controllers;

import com.example.conseil.entities.EmailRequest;
import com.example.conseil.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/api/email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
    }
    @Autowired
    private  JavaMailSender mailSender;

    @RequestMapping("/sendMail")
    public String sendMail() {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("hamimhajar85@gmail.com");
            message.setTo("simo2manbar@gmail.com");
            message.setSubject("test2");
            message.setText("test2");

            mailSender.send(message);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}


