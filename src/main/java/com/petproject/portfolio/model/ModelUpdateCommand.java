package com.petproject.portfolio.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelUpdateCommand implements ModelCommand {
    private String name;
    private Category category;
    private String imageUrl;
    private String description;
}
