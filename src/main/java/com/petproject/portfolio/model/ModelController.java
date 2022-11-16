package com.petproject.portfolio.model;

import com.petproject.portfolio.exception.NotFoundException;
import com.petproject.portfolio.model.container.ContainerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<ModelDto> update(@PathVariable Long id, @Valid @RequestBody ModelUpdateCommand command) throws NotFoundException {
        return ResponseEntity.ok(modelService.update(id, command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ModelDto> delete(@PathVariable Long id) throws NotFoundException {
        modelService.deleteModelById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/endorse")
    public ResponseEntity<ModelDto> endorse(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(modelService.endorseModel(id));
    }

    @GetMapping("/{id}/container/run")
    public ResponseEntity<ContainerInfo> runModelContainerById(@PathVariable Long id) throws InterruptedException {
        return ResponseEntity.ok(modelService.runModelContainerById(id));
    }
}
