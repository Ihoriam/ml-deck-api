package com.petproject.portfolio.model;

import com.petproject.portfolio.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.petproject.portfolio.utils.Utils.maybe;

@Getter
@Setter
public class ModelDto implements Serializable {
    private final Long id;
    private final String name;
    private final Category category;
    private final String dockerHubImageUrl;
    private final String description;
    private final UserDto createdBy;
    private final LocalDateTime createdAt;
    private final UserDto updatedBy;
    private final LocalDateTime updatedAt;

    public ModelDto(Model model) {
        this.id = model.getId();
        this.name = model.getName();
        this.category = model.getCategory();
        this.dockerHubImageUrl = model.getDockerHubImageUrl();
        this.description = model.getDescription();
        this.createdBy = maybe(model.getCreatedBy(), UserDto::new);
        this.createdAt = model.getCreatedAt();
        this.updatedBy = maybe(model.getUpdatedBy(), UserDto::new);
        this.updatedAt = model.getUpdatedAt();
    }
}
