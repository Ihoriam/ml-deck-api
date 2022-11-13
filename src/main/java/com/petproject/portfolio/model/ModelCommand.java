package com.petproject.portfolio.model;

public interface ModelCommand {
    String getName();
    String getImageUrl();
    String getDescription();
    Category getCategory();
}
