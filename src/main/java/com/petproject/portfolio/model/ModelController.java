package com.petproject.portfolio.model;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hello")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @GetMapping()
    public ResponseEntity<List<Model>> getAll() {
        return ResponseEntity.ok(modelService.getAll());
    }
}
