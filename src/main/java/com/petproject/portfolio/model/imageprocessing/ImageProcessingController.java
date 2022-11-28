package com.petproject.portfolio.model.imageprocessing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/image-processing")
public class ImageProcessingController {
    private final ImageProcessingService imageProcessingService;

    // process base64 image and return it
    @PostMapping("/process")
    public ResponseEntity<ProcessImageDto> processImage(@RequestBody @Valid ProcessImageCommand command) {
        // do something
        return ResponseEntity.ok(imageProcessingService.process(command));
    }

}
