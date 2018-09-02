package com.roman.mysan.app.user.asm;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.dto.UserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAccountAssembler {

    public static UserAccount convetToEntity(UserDTO userDTO) {
        return UserAccount.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .gender(userDTO.getGender())
                .enabled(false)
                .build();
    }
}
