package com.roman.mysan.app.user.utils;

import com.roman.mysan.app.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final IUserService service;
    private MessageSource messages;
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    @Async
    public void confirmRegistration(OnRegistrationCompleteEvent event) {
        var user = event.getUser();
        var token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        var recipientAddress = user.getEmail();
        var subject = "Registration Confirmation";
        var confirmationUrl = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
        var message = messages.getMessage("message.regSucc", null, event.getLocale());

        var email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
