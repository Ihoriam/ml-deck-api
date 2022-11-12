package com.petproject.portfolio.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthTokens {
    private final String accessToken;
    private String refreshToken;

    public AuthTokens(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
