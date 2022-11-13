package com.petproject.portfolio.utils;

import com.petproject.portfolio.user.User;
import com.petproject.portfolio.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class UserDetailsUtils {
    public static Optional<UserDetails> getUserDetails() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(UserDetails.class::isInstance)
                .map(UserDetails.class::cast);
    }

    public static Optional<User> getUser() {
        return getUserDetails()
                .flatMap(userDetails -> SpringUtils.getBean(UserRepository.class).findByUsername(userDetails.getUsername()));
    }
}

