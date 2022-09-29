package com.petproject.portfolio.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.HttpMethod;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.stream.Collectors;

@Log4j2
public class JwtUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtUsernamePasswordFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // By default, UsernamePasswordAuthenticationFilter listens to "/login" path.
        // So, we can to override the defaults.
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", HttpMethod.POST.name()));
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
//        todo:
        Algorithm algorithm = Algorithm.HMAC256("notverysecret".getBytes());
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(Instant.now().plusMillis(10 * 60 * 60))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(Instant.now().plusMillis(30 * 60 * 60))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        response.setHeader("access_token", accessToken);
        response.setHeader("refresh_token", refreshToken);
        log.info("Successful authentication with username " + request.getParameter("username"));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        log.info("Unsuccessful authentication with username " + request.getParameter("username"));
    }
}
