package com.roman.mysan.app.user.service;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.domain.VerificationToken;

public interface VerificationTokenService {

    VerificationToken createVerificationToken(UserAccount user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken updateToken(String oldToken, String newToken);
}
