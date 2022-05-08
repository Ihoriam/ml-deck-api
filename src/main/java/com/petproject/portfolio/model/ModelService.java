package com.petproject.portfolio.model;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    public List<ModelDto> getAll() {
        return modelRepository.findAll().stream().map(ModelDto::new).collect(Collectors.toList());
    }

    public ModelDto getById(Long id) throws NotFoundException {
        return modelRepository.findById(id)
                .map(ModelDto::new)
                .orElseThrow(() -> new NotFoundException("Model with id " + id + "does not exist"));
    }

}
