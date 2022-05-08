package com.petproject.portfolio.user;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Role role;
    private final Instant createdAt;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }
}
