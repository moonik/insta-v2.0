package com.roman.mysan.app.user.service;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.domain.VerificationToken;
import com.roman.mysan.app.user.repository.UserAccountRepository;
import com.roman.mysan.app.user.repository.VerificationTokenRepository;
import com.roman.mysan.app.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.roman.mysan.app.user.asm.UserAccountAssembler.convetToEntity;

@Service
@AllArgsConstructor
public class UserAccountService implements IUserService {

    private final UserAccountRepository userAccountRepository;
    private final VerificationTokenRepository tokenRepository;

    @Override
    public UserAccount createNewAccount(UserDTO userDTO) {
        return userAccountRepository.save(convetToEntity(userDTO));
    }

    @Override
    public void saveRegisteredUser(UserAccount user) {
        userAccountRepository.save(user);
    }

    @Override
    public UserAccount getUser(String token) {
        return tokenRepository.findByToken(token).getUser();
    }

    @Override
    public void createVerificationToken(UserAccount user, String token) {
        tokenRepository.save(new VerificationToken(token, user));
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
