package com.roman.mysan.app.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter @Setter
public class UserDTO {

    @NotNull
    private String email;
    @NotNull
    private String password;
    private String name;
    private String surname;
    private Character gender;
}
