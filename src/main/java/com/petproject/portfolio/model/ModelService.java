package com.petproject.portfolio.model;

import com.petproject.portfolio.exception.NotFoundException;
import com.petproject.portfolio.model.container.ContainerInfo;
import com.petproject.portfolio.model.container.ContainerProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.GenericContainer;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    @Transactional(readOnly = true)
    public List<ModelDto> getAll() {
        return modelRepository.findAll().stream().map(ModelDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ModelDto getById(Long id) throws NotFoundException {
        return modelRepository.findById(id)
                .map(ModelDto::new)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
    }

    @Transactional
    public ModelDto create(ModelCreateCommand command) {
        Model model = new Model();
        model.mapPrimitiveFields(command);
        return new ModelDto(modelRepository.save(model));
    }

    @Transactional
    public ModelDto update(long id, ModelUpdateCommand command) throws NotFoundException {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
        model.mapPrimitiveFields(command);
        return new ModelDto(modelRepository.save(model));
    }

    @Transactional
    public void deleteModelById(long id) throws NotFoundException {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
        model.setDeleted(true);
        modelRepository.save(model);
    }

    @Transactional
    // todo: think about thread safety (data race)
    public ModelDto endorseModel(long id) throws NotFoundException {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
        model.incrementEndorsementCount();
        return new ModelDto(modelRepository.save(model));
    }

    public ContainerInfo runModelContainerById(Long id) throws InterruptedException {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));

        GenericContainer container = ContainerProvider.getContainerByDockerHubUrl(model.getDockerHubImageUrl());
        container.start();
        // 30 second
        Thread.sleep(1 * 60 * 1000 / 2);
        return new ContainerInfo(container);
    }
}
