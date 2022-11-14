package com.petproject.portfolio.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ModelCreateCommand implements ModelCommand {
    @NotEmpty
    private String name;
    @NotEmpty
    private Category category;
    @NotEmpty
    private String dockerHubImageUrl;
    private String description;
}
