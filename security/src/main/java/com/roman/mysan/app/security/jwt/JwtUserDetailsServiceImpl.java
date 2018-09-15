package com.roman.mysan.app.security.jwt;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService{

    private final UserAccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserAccount> userAccount = userRepository.findByEmail(username);

        if(!userAccount.isPresent()) {
            throw new UsernameNotFoundException(String.format("No userAccount found with username: '%s'.", username));
        } else {
            return JwtUserFactory.create(userAccount.get());
        }
    }
}
