package com.github.souzafcharles.api.controller;

import com.github.souzafcharles.api.model.dto.RuralPropertyRequestDTO;
import com.github.souzafcharles.api.model.dto.RuralPropertyResponseDTO;
import com.github.souzafcharles.api.service.RuralPropertyService;
import com.github.souzafcharles.api.util.RuralPropertyMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RuralPropertyController {

    @Autowired
    private RuralPropertyService service;

    @PostMapping
    public ResponseEntity<RuralPropertyResponseDTO> create(@RequestBody RuralPropertyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }

    @GetMapping
    public ResponseEntity<List<RuralPropertyResponseDTO>> readAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RuralPropertyResponseDTO> readOne(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RuralPropertyResponseDTO> update(@PathVariable Long id, @RequestBody RuralPropertyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(RuralPropertyMessages.PROPERTY_DELETED_SUCCESSFULLY);
    }
}
