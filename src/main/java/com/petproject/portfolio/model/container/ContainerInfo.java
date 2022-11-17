package com.petproject.portfolio.model.container;

import lombok.Getter;
import lombok.Setter;
import org.testcontainers.containers.GenericContainer;

@Getter
@Setter
public class ContainerInfo {

    private boolean dockerHubAvailable;
    private boolean dockerContainerActive;

    public ContainerInfo(GenericContainer container) {
        // todo: get info using Docker Hub API
        // example
        https://hub.docker.com/v2/repositories/bluewhitebox/flask-api-one
        this.dockerHubAvailable = true;
        this.dockerContainerActive = container.isRunning();
    }

}
