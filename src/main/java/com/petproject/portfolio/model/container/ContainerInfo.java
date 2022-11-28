package com.petproject.portfolio.model.container;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.testcontainers.containers.GenericContainer;

@Getter
@Setter
@NoArgsConstructor
public class ContainerInfo {
    private boolean dockerContainerActive;

    public ContainerInfo(GenericContainer container) {
        this.dockerContainerActive = container.isRunning();
    }

}
