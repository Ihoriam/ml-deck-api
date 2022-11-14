package com.petproject.portfolio.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ModelUpdateCommand implements ModelCommand {
    @NotEmpty
    private String name;
    @NotEmpty
    private Category category;
    @NotEmpty
    private String dockerHubImageUrl;
    private String description;
}
