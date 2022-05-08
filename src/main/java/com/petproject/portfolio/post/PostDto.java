package com.petproject.portfolio.post;

import com.petproject.portfolio.user.UserDto;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class PostDto implements Serializable {
    private final Long id;
    private final String title;
    private final String content;
    private final Boolean deleted;
    private final UserDto author;
    private final Instant createdAt;
}
