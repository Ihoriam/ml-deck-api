package com.petproject.portfolio.model.container;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class ContainerProvider {

    private static Map<String, GenericContainer> containerByDockerHubUrl = new HashMap<>();

    public static GenericContainer getContainerByDockerHubUrl(String dockerHubUrl) {
        if (!containerByDockerHubUrl.containsKey(dockerHubUrl)) {
            return createContainerByDockerHubUrl(dockerHubUrl);
        }
        return containerByDockerHubUrl.get(dockerHubUrl);
    }

    private static GenericContainer createContainerByDockerHubUrl(String dockerHubUrl) {
        GenericContainer container = new GenericContainer(DockerImageName.parse(dockerHubUrl))
//                .withExposedPorts(5000)
                .waitingFor(Wait.forHttp("/"))
                .withStartupTimeout(Duration.ofSeconds(20))
//                .withAccessToHost(true)
                .withReuse(true);
        containerByDockerHubUrl.put(dockerHubUrl, container);
        return container;
    }
}
