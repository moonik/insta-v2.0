package com.roman.mysan.app.user.controller;

import com.roman.mysan.app.user.service.UserAccountService;
import com.roman.mysan.app.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Log
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/create")
    public void create(@Valid @RequestBody UserDTO userDTO) {
        userAccountService.createNewAccount(userDTO);
    }

    @GetMapping("/registration-confirmation")
    public void confirmRegistration(@RequestParam("token") String token) throws AuthenticationException {
        userAccountService.confirmRegistration(token);
    }

    @PostMapping("/resend-token")
    public void resendToken(@RequestParam("token") String token) {
        userAccountService.resendEmailWithToken(token);
    }
}
