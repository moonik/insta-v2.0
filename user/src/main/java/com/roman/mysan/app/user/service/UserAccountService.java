package com.roman.mysan.app.user.service;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.dto.UserDTO;

import javax.naming.AuthenticationException;

public interface UserAccountService {

    void createNewAccount(UserDTO userDTO);

    void resendEmailWithToken(String oldToken);

    void saveRegisteredUser(UserAccount user);

    void confirmRegistration(String token) throws AuthenticationException;
}
