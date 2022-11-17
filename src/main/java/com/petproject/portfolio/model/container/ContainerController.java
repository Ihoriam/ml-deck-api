package com.petproject.portfolio.model.container;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/models")
public class ContainerController {
    private final ContainerService containerService;

    @GetMapping("/{id}/container")
    public ResponseEntity<ContainerInfo> getContainerInfoByModelId(@PathVariable Long id) {
        return ResponseEntity.ok(containerService.getContainerInfoByModelId(id));
    }

    @GetMapping("/{id}/container/run")
    public ResponseEntity<ContainerInfo> runContainerByModelId(@PathVariable Long id) throws InterruptedException {
        return ResponseEntity.ok(containerService.runContainerByModelId(id));
    }

    @GetMapping("/{id}/container/stop")
    public ResponseEntity<ContainerInfo> stopContainerByModelId(@PathVariable Long id) throws InterruptedException {
        return ResponseEntity.ok(containerService.stopContainerByModelId(id));
    }

    @GetMapping("/{id}/container/response")
    public ResponseEntity<Void> getContainerResponseByModelId(@PathVariable Long id) throws InterruptedException {
        containerService.getContainerResponseByModelId(id);
        return ResponseEntity.ok().build();
    }
}
