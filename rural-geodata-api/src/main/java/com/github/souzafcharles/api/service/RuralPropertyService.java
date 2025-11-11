package com.github.souzafcharles.api.service;

import com.github.souzafcharles.api.model.dto.RuralPropertyRequestDTO;
import com.github.souzafcharles.api.model.dto.RuralPropertyResponseDTO;
import com.github.souzafcharles.api.model.entities.RuralProperty;
import com.github.souzafcharles.api.repository.RuralPropertyRepository;
import com.github.souzafcharles.api.util.RuralPropertyMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RuralPropertyService {

    @Autowired
    private RuralPropertyRepository repository;

    @Transactional(readOnly = true)
    public List<RuralPropertyResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(RuralPropertyResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public RuralPropertyResponseDTO findById(Long id) {
        RuralProperty entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(RuralPropertyMessages.PROPERTY_NOT_FOUND));
        return new RuralPropertyResponseDTO(entity);
    }

    @Transactional
    public RuralPropertyResponseDTO insert(RuralPropertyRequestDTO dto) {
        RuralProperty entity = new RuralProperty(
                dto.name(),
                dto.latitude(),
                dto.longitude(),
                dto.areaHectares()
        );
        entity = repository.save(entity);
        return new RuralPropertyResponseDTO(entity);
    }

    @Transactional
    public RuralPropertyResponseDTO update(Long id, RuralPropertyRequestDTO dto) {
        RuralProperty entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(RuralPropertyMessages.PROPERTY_NOT_FOUND));
        entity.setName(dto.name());
        entity.setLatitude(dto.latitude());
        entity.setLongitude(dto.longitude());
        entity.setAreaHectares(dto.areaHectares());
        entity = repository.save(entity);
        return new RuralPropertyResponseDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(RuralPropertyMessages.PROPERTY_NOT_FOUND);
        }
        repository.deleteById(id);
    }
}