package com.petproject.portfolio.model.imageprocessing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageProcessingService {

    // process base64 image and return it
    public ProcessImageDto process(ProcessImageCommand command) {

        return new ProcessImageDto(command.getBase64Image());
    }

}
