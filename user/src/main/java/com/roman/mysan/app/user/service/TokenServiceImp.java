package com.roman.mysan.app.user.service;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.domain.VerificationToken;
import com.roman.mysan.app.user.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenServiceImp implements VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;

    @Override
    public VerificationToken createVerificationToken(UserAccount user, String token) {
        var verificationToken = new VerificationToken(token, user);
        verificationToken.setExpDate();
        return tokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public VerificationToken updateToken(String oldToken, String newToken) {
        var token = tokenRepository.findByToken(oldToken);
        token.setExpDate();
        token.setToken(newToken);
        return tokenRepository.save(token);
    }
}
