package com.roman.mysan.app.user.controller;

import com.roman.mysan.app.user.service.IUserService;
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

    private final IUserService userService;

    @PostMapping("/create")
    public void create(@Valid @RequestBody UserDTO userDTO) {
        userService.createNewAccount(userDTO);
    }

    @GetMapping("/registration-confirmation")
    public void confirmRegistration(@RequestParam("token") String token) throws AuthenticationException {
        userService.confirmRegistration(token);
    }

    @PostMapping("/resend-token")
    public void resendToken(@RequestParam("token") String token) {
        userService.resendEmailWithToken(token);
    }
}
