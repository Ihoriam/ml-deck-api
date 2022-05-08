package com.petproject.portfolio;

import com.petproject.portfolio.post.PostDto;
import com.petproject.portfolio.user.UserDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDto implements Serializable {
    private final Long id;
    private final String content;
    private final Boolean deleted;
    private final UserDto author;
    private final PostDto post;
}
