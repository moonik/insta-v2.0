package com.roman.mysan.app.user.service;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.domain.RegistrationToken;
import com.roman.mysan.app.user.repository.RegistrationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationTokenServiceImp implements RegistrationTokenService {

    private final RegistrationTokenRepository tokenRepository;

    @Override
    public RegistrationToken createVerificationToken(UserAccount user, String token) {
        var verificationToken = new RegistrationToken(token, user);
        verificationToken.setExpiryDate();
        return tokenRepository.save(verificationToken);
    }

    @Override
    public RegistrationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public RegistrationToken updateToken(String oldToken, String newToken) {
        var token = tokenRepository.findByToken(oldToken);
        token.setExpiryDate();
        token.setToken(newToken);
        return tokenRepository.save(token);
    }
}
