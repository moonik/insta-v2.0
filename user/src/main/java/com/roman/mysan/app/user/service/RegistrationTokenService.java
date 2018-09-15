package com.roman.mysan.app.user.service;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.domain.RegistrationToken;

public interface RegistrationTokenService {

    RegistrationToken createVerificationToken(UserAccount user, String token);

    RegistrationToken getVerificationToken(String VerificationToken);

    RegistrationToken updateToken(String oldToken, String newToken);
}
