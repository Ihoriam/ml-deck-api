package com.petproject.portfolio.model.imageprocessing;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ProcessImageCommand {
    @NotEmpty
    private String base64Image;
}
