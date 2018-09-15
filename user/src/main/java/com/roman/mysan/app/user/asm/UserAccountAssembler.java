package com.roman.mysan.app.user.asm;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.dto.UserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAccountAssembler {

    public static UserAccount convertToEntity(UserDTO userDTO) {
        return UserAccount.builder()
                .email(userDTO.getEmail())
                .password(new BCryptPasswordEncoder().encode(userDTO.getPassword()))
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .gender(userDTO.getGender())
                .enabled(false)
                .build();
    }
}
