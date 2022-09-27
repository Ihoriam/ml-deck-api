package com.petproject.portfolio.jwt;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
