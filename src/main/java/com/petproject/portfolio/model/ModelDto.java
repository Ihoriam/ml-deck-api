package com.petproject.portfolio.model;

import com.petproject.portfolio.user.UserDto;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ModelDto implements Serializable {
    private final Long id;
    private final String name;
    private final UserDto author;
    private final Category category;
    private final String imageUrl;
    private final LocalDate createdAt;

    public ModelDto(Model model) {
        this.id = model.getId();
        this.name = model.getName();
        this.author = new UserDto(model.getAuthor());
        this.category = model.getCategory();
        this.imageUrl = model.getImageUrl();
        this.createdAt = model.getCreatedAt().toLocalDate();
    }
}
