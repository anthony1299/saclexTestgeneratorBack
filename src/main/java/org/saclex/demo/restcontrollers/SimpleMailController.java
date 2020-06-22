package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/email")
@CrossOrigin("*")
public class SimpleMailController {


    public JavaMailSender javaMailSender;

    @PostMapping(value = "/sendEmail")
    public String sendMail(@RequestBody Feedback feedback){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("fouda.anthony12@gmail.com");
        message.setSubject(feedback.getSubject());
        message.setText("<"+feedback.getEmail()+"> "+feedback.getFeedback());

        javaMailSender.send(message);

        return "mail envoye avec succes";

    }

}
