package com.petproject.portfolio.model.container;

import com.petproject.portfolio.dockerhub.DockerHubService;
import com.petproject.portfolio.exception.NotFoundException;
import com.petproject.portfolio.model.Model;
import com.petproject.portfolio.model.ModelRepository;
import com.petproject.portfolio.utils.resttemplates.PreparedRestTemplate;
import com.petproject.portfolio.utils.resttemplates.RestTemplateExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.testcontainers.containers.GenericContainer;

@Service
@RequiredArgsConstructor
public class ContainerService {
    private final ModelRepository modelRepository;
    private final DockerHubService dockerHubService;

    public ContainerResponse getResponseFromContainerByModelId(Long id, PromptCommand command) throws NotFoundException {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
        GenericContainer container = ContainerProvider.runContainerByDockerHubUrl(model.getDockerHubImageUrl());
        String url = "http://" + container.getHost() + ":" + container.getMappedPort(5000) + "/api/ml-deck/process";
        String prompt = command.getPrompt();
        String cuttedPrompt = prompt.substring("data:image/png;base64,".length());
        PreparedRestTemplate preparedRestTemplate = PreparedRestTemplate.builder()
                .url(url)
                .httpMethod(HttpMethod.POST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ContainerRequest.builder().prompt(cuttedPrompt).build())
                .build();
        ContainerResponse response = RestTemplateExecutor.exchange(preparedRestTemplate, ContainerResponse.class).getBody();
        return response;
    }

    public ContainerInfo getContainerInfoByModelId(long id) throws NotFoundException {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
        ContainerInfo containerInfo = new ContainerInfo();
        if (!dockerHubService.isDockerHubImageExist(model.getDockerHubImageUrl())) {
            containerInfo.setDockerContainerActive(false);
            return containerInfo;
        }
        GenericContainer container = ContainerProvider.getLoadedContainerByByDockerHubUrl(model.getDockerHubImageUrl());
        if (container == null) {
            containerInfo.setDockerContainerActive(false);
            return containerInfo;
        }
        return new ContainerInfo(container);
    }

//    public ContainerInfo runContainerByModelId(Long id) throws InterruptedException {
//        Model model = modelRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
//
//        GenericContainer container = ContainerProvider.runContainerByDockerHubUrl(model.getDockerHubImageUrl());
//        if (!container.isRunning()) {
//            container.start();
//        }
//        return new ContainerInfo(container);
//    }
//
//    public ContainerInfo stopContainerByModelId(Long id) {
//        Model model = modelRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
//
//        GenericContainer container = ContainerProvider.runContainerByDockerHubUrl(model.getDockerHubImageUrl());
//        if (container.isRunning()) {
//            container.stop();
//        }
//        return new ContainerInfo(container);
//    }


}
