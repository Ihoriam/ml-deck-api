package com.petproject.portfolio.model.container;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/models")
public class ContainerController {
    private final ContainerService containerService;

    @PostMapping("/{id}/container/response")
    public ResponseEntity<ContainerResponse> getContainerResponseByModelId(@PathVariable Long id,
                                                                           @RequestBody PromptCommand promptCommand) {
        return ResponseEntity.ok(containerService.getResponseFromContainerByModelId(id, promptCommand));
    }

    @GetMapping("/{id}/container/info")
    public ResponseEntity<ContainerInfo> getContainerInfoByModelId(@PathVariable Long id) {
        return ResponseEntity.ok(containerService.getContainerInfoByModelId(id));
    }

//    @GetMapping("/{id}/container/run")
//    public ResponseEntity<ContainerInfo> runContainerByModelId(@PathVariable Long id) throws InterruptedException {
//        return ResponseEntity.ok(containerService.runContainerByModelId(id));
//    }
//
//    @GetMapping("/{id}/container/stop")
//    public ResponseEntity<ContainerInfo> stopContainerByModelId(@PathVariable Long id) throws InterruptedException {
//        return ResponseEntity.ok(containerService.stopContainerByModelId(id));
//    }
}
