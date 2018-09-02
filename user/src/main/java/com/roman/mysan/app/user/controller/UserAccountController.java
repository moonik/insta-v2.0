package com.roman.mysan.app.user.controller;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.service.IUserService;
import com.roman.mysan.app.user.domain.VerificationToken;
import com.roman.mysan.app.user.dto.UserDTO;
import com.roman.mysan.app.user.service.NotificationService;
import com.roman.mysan.app.user.utils.OnRegistrationCompleteEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserAccountController {

    private final ApplicationEventPublisher eventPublisher;
    private final IUserService userService;
    private final MessageSource messages;
    private final NotificationService notificationService;

    @PostMapping("/create")
    public void create(@Valid @RequestBody UserDTO userDTO) throws InterruptedException {
        final var user = userService.createNewAccount(userDTO);
//        final var event = new OnRegistrationCompleteEvent(user, "url", Locale.ENGLISH);
//        eventPublisher.publishEvent(event);
        notificationService.sendNotificaitoin(user);
    }

    @GetMapping("/registration-confirmation")
    public String confirmRegistration(@RequestParam("token") String token) {
        Locale locale = Locale.ENGLISH;

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return messages.getMessage("auth.message.invalidToken", null, locale);
        }

        UserAccount user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return messages.getMessage("auth.message.expired", null, locale);
        }
        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return "success!";
    }
}
