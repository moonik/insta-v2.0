package com.roman.mysan.app.user.service;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.domain.VerificationToken;
import com.roman.mysan.app.user.repository.UserAccountRepository;
import com.roman.mysan.app.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Calendar;
import java.util.UUID;

import static com.roman.mysan.app.user.asm.UserAccountAssembler.convetToEntity;

@Service
@AllArgsConstructor
@Log
public class UserAccountService implements IUserService {

    private final UserAccountRepository userAccountRepository;
    private final VerificationTokenService tokenService;
    private final MailSenderService mailSenderService;

    @Override
    public void createNewAccount(UserDTO userDTO) {
        var user = userAccountRepository.save(convetToEntity(userDTO));
        var tokenValue = generateToken();
        tokenService.createVerificationToken(user, tokenValue);
        mailSenderService.sendEmail(user, tokenValue);
    }

    @Override
    public void resendEmailWithToken(String oldToken) {
        var tokenValue = generateToken();
        var newToken = tokenService.updateToken(oldToken, tokenValue);
        mailSenderService.sendEmail(newToken.getUser(), tokenValue);
    }

    @Override
    public void saveRegisteredUser(UserAccount user) {
        userAccountRepository.save(user);
    }

    @Override
    public void confirmRegistration(String token) throws AuthenticationException {
        log.info("Verifying token...");

        VerificationToken verificationToken = tokenService.getVerificationToken(token);
        if (verificationToken == null) {
            log.info("Invalid token");
            throw new AuthenticationException("Invalid token");
        }

        UserAccount user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            log.info("Expired token");
            throw new AuthenticationException("Expired token");
        }
        user.setEnabled(true);
        saveRegisteredUser(user);

        log.info("Successful confirmation of registration.");
    }

    private static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
