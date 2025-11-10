package com.github.souzafcharles.api.model.dto;

import com.github.souzafcharles.api.model.entities.RuralProperty;

public record RuralPropertyResponseDTO(Long id, String name, Double latitude, Double longitude, Double areaHectares) {

    public RuralPropertyResponseDTO (RuralProperty ruralProperty){
    this(ruralProperty.getId(), ruralProperty.getName(), ruralProperty.getLatitude(), ruralProperty.getLongitude(), ruralProperty.getAreaHectares());
    }
}
