package com.petproject.portfolio.user;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String username;
    private final String email;
    private final Instant createdAt;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }
}
