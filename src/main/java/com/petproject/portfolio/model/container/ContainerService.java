package com.petproject.portfolio.model.container;

import com.petproject.portfolio.exception.NotFoundException;
import com.petproject.portfolio.model.Model;
import com.petproject.portfolio.model.ModelRepository;
import com.petproject.portfolio.utils.resttemplates.PreparedRestTemplate;
import com.petproject.portfolio.utils.resttemplates.RestTemplateExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.testcontainers.containers.GenericContainer;

@Service
@RequiredArgsConstructor
public class ContainerService {
    private final ModelRepository modelRepository;

    public ContainerInfo getContainerInfoByModelId(long id) throws NotFoundException {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
        ContainerProvider.getContainerByDockerHubUrl(model.getDockerHubImageUrl());
        return new ContainerInfo(ContainerProvider.getContainerByDockerHubUrl(model.getDockerHubImageUrl()));
    }

    public ContainerInfo runContainerByModelId(Long id) throws InterruptedException {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));

        GenericContainer container = ContainerProvider.getContainerByDockerHubUrl(model.getDockerHubImageUrl());
        if (!container.isRunning()) {
            container.start();
        }
        return new ContainerInfo(container);
    }

    public ContainerInfo stopContainerByModelId(Long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));

        GenericContainer container = ContainerProvider.getContainerByDockerHubUrl(model.getDockerHubImageUrl());
        if (container.isRunning()) {
            container.stop();
        }
        return new ContainerInfo(container);
    }

    public void getContainerResponseByModelId(Long id) throws NotFoundException {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
        GenericContainer container = ContainerProvider.getContainerByDockerHubUrl(model.getDockerHubImageUrl());
        String baseContainerAddress = container.getHost() + ":" + container.getMappedPort(5000);
        System.out.println(baseContainerAddress);
    }


}
