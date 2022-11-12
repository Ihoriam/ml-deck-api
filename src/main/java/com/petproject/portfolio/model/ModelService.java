package com.petproject.portfolio.model;

import com.petproject.portfolio.user.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;

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
    public ModelDto create(ModelCreateCommand command) throws NotFoundException {
        Model model = new Model();
        model.setName(command.getName());
        model.setCategory(command.getCategory());
        model.setImageUrl(command.getImageUrl());
        return new ModelDto(modelRepository.save(model));
    }

    @Transactional
    public ModelDto update(long id, ModelUpdateCommand command) throws NotFoundException {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
        model.setName(command.getName());
        model.setCategory(command.getCategory());
        model.setImageUrl(command.getImageUrl());
        return new ModelDto(modelRepository.save(model));
    }

    @Transactional
    public ModelDto delete(long id) throws NotFoundException {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
        model.setDeleted(true);
        return new ModelDto(model);
    }
}
