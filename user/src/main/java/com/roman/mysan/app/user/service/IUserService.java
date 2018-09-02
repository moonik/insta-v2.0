package com.roman.mysan.app.user.service;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.domain.VerificationToken;
import com.roman.mysan.app.user.dto.UserDTO;

public interface IUserService {

    UserAccount createNewAccount(UserDTO userDTO);

    void saveRegisteredUser(UserAccount user);

    UserAccount getUser(String token);

    void createVerificationToken(UserAccount user, String token);

    VerificationToken getVerificationToken(String VerificationToken);
}
