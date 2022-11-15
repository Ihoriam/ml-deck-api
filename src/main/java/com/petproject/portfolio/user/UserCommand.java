package com.petproject.portfolio.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserCommand {
    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;
    @NotEmpty
    @Size(min = 6, max = 20)
    private String password;
    @Email
    @NotEmpty
    private String email;
    private String emoji;
}
