package com.roman.mysan.app.user.utils;

import com.roman.mysan.app.user.domain.UserAccount;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter @Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private UserAccount user;

    public OnRegistrationCompleteEvent(UserAccount user, String appUrl, Locale locale) {
        super(user);
        this.appUrl = appUrl;
        this.locale = locale;
    }
}
