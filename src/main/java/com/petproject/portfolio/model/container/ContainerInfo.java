package com.petproject.portfolio.model.container;

import com.petproject.portfolio.dockerhub.DockerHubService;
import com.petproject.portfolio.utils.SpringUtils;
import lombok.Getter;
import lombok.Setter;
import org.testcontainers.containers.GenericContainer;

@Getter
@Setter
public class ContainerInfo {

    private boolean dockerHubAvailable;
    private boolean dockerContainerActive;

    public ContainerInfo(GenericContainer container) {
        this.dockerHubAvailable = SpringUtils.getBean(DockerHubService.class)
                .isDockerHubImageExist(container.getDockerImageName());
        this.dockerContainerActive = container.isRunning();
    }

}
