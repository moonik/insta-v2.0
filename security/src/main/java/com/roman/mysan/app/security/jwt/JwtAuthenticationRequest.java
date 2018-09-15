package com.roman.mysan.app.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class JwtAuthenticationRequest {

    private String email;
    private String password;
}
