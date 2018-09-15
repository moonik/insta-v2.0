package com.roman.mysan.app.security;

import com.roman.mysan.app.user.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.roman.mysan.app.security.JwtAuthenticationTokenFilter.HEADER_STRING;

@RestController
@RequestMapping("/api/insta")
@AllArgsConstructor
public class AuthenticationController {

    protected UserAccountRepository userAccountRepository;

    protected AuthenticationManager authenticationManager;

    protected JwtTokenUtil jwtTokenUtil;

    protected JwtUserDetailsServiceImpl jwtUserDetailsService;

    @PostMapping("/auth")
    public ResponseEntity createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest jwtAuthenticationRequest,
            HttpServletResponse httpServletResponse) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtAuthenticationRequest.getEmail(),
                        jwtAuthenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generate token
        final var userDetails = jwtUserDetailsService.loadUserByUsername(jwtAuthenticationRequest.getEmail());
        final var token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @PostMapping("/refresh")
    public ResponseEntity refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if(jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
