package com.petproject.portfolio.model;

import com.petproject.portfolio.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ModelDto implements Serializable {
    private final Long id;
    private final String name;
    private final UserDto author;
    private final Category category;
    private final String imageUrl;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ModelDto(Model model) {
        this.id = model.getId();
        this.name = model.getName();
        this.author = new UserDto(model.getCreatedBy());
        this.category = model.getCategory();
        this.imageUrl = model.getImageUrl();
        this.createdAt = model.getCreatedAt();
        this.updatedAt = model.getUpdatedAt();
    }
}
