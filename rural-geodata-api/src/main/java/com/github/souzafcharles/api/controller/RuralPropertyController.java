package com.github.souzafcharles.api.controller;

import com.github.souzafcharles.api.model.dto.RuralPropertyRequestDTO;
import com.github.souzafcharles.api.model.dto.RuralPropertyResponseDTO;
import com.github.souzafcharles.api.service.RuralPropertyService;
import com.github.souzafcharles.api.util.RuralPropertyMessages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = RuralPropertyMessages.OPENAPI_TITLE, description = RuralPropertyMessages.OPENAPI_DESCRIPTION)
public class RuralPropertyController {

    @Autowired
    private RuralPropertyService service;

    @Operation(summary = RuralPropertyMessages.SUMMARY_CREATE_PROPERTY, description = RuralPropertyMessages.DESCRIPTION_CREATE_PROPERTY)
    @PostMapping
    public ResponseEntity<RuralPropertyResponseDTO> create(@Valid @RequestBody RuralPropertyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }

    @Operation(summary = RuralPropertyMessages.SUMMARY_GET_ALL_PROPERTIES, description = RuralPropertyMessages.DESCRIPTION_GET_ALL_PROPERTIES)
    @GetMapping
    public ResponseEntity<List<RuralPropertyResponseDTO>> readAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @Operation(summary = RuralPropertyMessages.SUMMARY_GET_PROPERTY_BY_ID, description = RuralPropertyMessages.DESCRIPTION_GET_PROPERTY_BY_ID)
    @GetMapping("/{id}")
    public ResponseEntity<RuralPropertyResponseDTO> readOne(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @Operation(summary = RuralPropertyMessages.SUMMARY_UPDATE_PROPERTY, description = RuralPropertyMessages.DESCRIPTION_UPDATE_PROPERTY)
    @PutMapping("/{id}")
    public ResponseEntity<RuralPropertyResponseDTO> update(@PathVariable Long id, @Valid @RequestBody RuralPropertyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
    }

    @Operation(summary = RuralPropertyMessages.SUMMARY_DELETE_PROPERTY, description = RuralPropertyMessages.DESCRIPTION_DELETE_PROPERTY)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(RuralPropertyMessages.PROPERTY_DELETED_SUCCESSFULLY);
    }
}