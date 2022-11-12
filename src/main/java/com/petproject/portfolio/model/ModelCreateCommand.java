package com.petproject.portfolio.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelCreateCommand {
    private String name;
    private Category category;
    private String imageUrl;
}
