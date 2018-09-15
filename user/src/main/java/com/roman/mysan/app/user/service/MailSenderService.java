package com.roman.mysan.app.user.service;

import com.roman.mysan.app.user.domain.UserAccount;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log
public class MailSenderService {

    private final JavaMailSender javaMailSender;
    private final static String VERIFICATION_URL = "http://localhost:8080/api/insta/user/registration-confirmation?token=";

    @Async
    public void sendEmail(UserAccount user, String token) throws MailException {
        log.info("Trying to send email...");

        var mail = new SimpleMailMessage();
        var verificationLink = VERIFICATION_URL + token;

        mail.setTo(user.getEmail());
        mail.setSubject("Insta app email verification");
        mail.setText("In order to complete the registration process, click on the link bellow.\n" + verificationLink);

        javaMailSender.send(mail);

        log.info("Email was sent...");
    }
}
