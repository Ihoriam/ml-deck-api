package com.petproject.portfolio.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.stream.Collectors;


@Component
public class JwtTokenProvider {
    @Value("${app.jwt.secret}")
    private String secret;
    private Algorithm algorithm;

    @PostConstruct
    private void assignAlgorithm() {
        algorithm = Algorithm.HMAC256(secret.getBytes());
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public AuthTokens generateTokens(UserDetails userDetails) {
        String accessToken = JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(Instant.now().plusMillis(10 * 60 * 60))
                .withIssuer("Portfolio app")
                .sign(algorithm);
        String refreshToken = JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(Instant.now().plusMillis(30 * 60 * 60))
                .withIssuer("Portfolio app")
                .sign(algorithm);
        return new AuthTokens(accessToken, refreshToken);
    }
}
