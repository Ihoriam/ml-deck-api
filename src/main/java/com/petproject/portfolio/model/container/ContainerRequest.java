package com.petproject.portfolio.model.container;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContainerRequest {
    private String prompt;
}
