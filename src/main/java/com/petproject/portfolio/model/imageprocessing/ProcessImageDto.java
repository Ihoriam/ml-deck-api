package com.petproject.portfolio.model.imageprocessing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class ProcessImageDto {
    private String base64Image;
}
