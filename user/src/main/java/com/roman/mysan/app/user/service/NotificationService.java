package com.roman.mysan.app.user.service;

import com.roman.mysan.app.user.domain.UserAccount;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendNotificaitoin(UserAccount user) throws MailException, InterruptedException {

        System.out.println("Sleeping now...");
        Thread.sleep(10000);

        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("danvega@gmail.com");
        mail.setSubject("Spring Boot is awesome!");
        mail.setText("Why aren't you using Spring Boot?");
        javaMailSender.send(mail);

        System.out.println("Email Sent!");
    }

}
