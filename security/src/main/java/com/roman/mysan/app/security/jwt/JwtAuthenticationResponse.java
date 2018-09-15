package com.roman.mysan.app.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtAuthenticationResponse {
    private final String token;
}
