package com.petproject.portfolio.model.container;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;


public class ContainerProvider {

    // todo: does it needed
    private static Map<String, GenericContainer> containerByDockerHubUrl = new HashMap<>();

    public static GenericContainer getLoadedContainerByByDockerHubUrl(String dockerHubUrl) {
        return containerByDockerHubUrl.get(dockerHubUrl);
    }

    public static GenericContainer runContainerByDockerHubUrl(String dockerHubUrl) {
        if (!containerByDockerHubUrl.containsKey(dockerHubUrl)) {
            return createContainerByDockerHubUrl(dockerHubUrl);
        }
        GenericContainer container = containerByDockerHubUrl.get(dockerHubUrl);
        if (!container.isRunning()) {
            container.start();
        }
        return container;
    }

    private static GenericContainer createContainerByDockerHubUrl(String dockerHubUrl) {
        GenericContainer container = new GenericContainer(DockerImageName.parse(dockerHubUrl))
                .withExposedPorts(5000)
//                .waitingFor(Wait.forHttp("/"))
//                .withStartupTimeout(Duration.ofSeconds(20))
//                .withAccessToHost(true)
                .withReuse(true);
        container.start();
        containerByDockerHubUrl.put(dockerHubUrl, container);
        return container;
    }
}
