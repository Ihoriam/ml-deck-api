package com.petproject.portfolio.dockerhub;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/dockerhub")
public class DockerHubController {
    private final DockerHubService dockerHubService;

    @GetMapping("/repositories/check")
    public ResponseEntity<DockerHubInfo> isDockerHubRepoExist(@RequestParam String name) {
        return ResponseEntity.ok(dockerHubService.getInfoAboutDockerHubImageByName(name));
    }
}
