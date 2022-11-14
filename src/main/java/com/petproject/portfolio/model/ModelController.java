package com.petproject.portfolio.model;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @GetMapping()
    public ResponseEntity<List<ModelDto>> getAll() {
        return ResponseEntity.ok(modelService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelDto> getById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(modelService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ModelDto> create(@RequestBody @Validated ModelCreateCommand command) throws NotFoundException {
        return ResponseEntity.ok(modelService.create(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelDto> update(@PathVariable Long id, @RequestBody ModelUpdateCommand command) throws NotFoundException {
        return ResponseEntity.ok(modelService.update(id, command));
    }
}
