package com.petproject.portfolio.model;

public interface ModelCommand {
    String getName();
    String getDockerHubImageUrl();
    String getDescription();
    Category getCategory();
}
