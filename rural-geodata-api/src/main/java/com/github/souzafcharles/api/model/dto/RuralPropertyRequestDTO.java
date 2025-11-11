package com.github.souzafcharles.api.model.dto;

import com.github.souzafcharles.api.util.RuralPropertyMessages;
import jakarta.validation.constraints.*;

public record RuralPropertyRequestDTO(
        Long id,

        @NotBlank(message = RuralPropertyMessages.PROPERTY_NAME_REQUIRED)
        @Size(max = 255, message = RuralPropertyMessages.PROPERTY_NAME_SIZE_EXCEEDED)
        String name,

        @DecimalMin(value = "-90.0", message = RuralPropertyMessages.LATITUDE_RANGE_INVALID)
        @DecimalMax(value = "90.0", message = RuralPropertyMessages.LATITUDE_RANGE_INVALID)
        Double latitude,

        @DecimalMin(value = "-180.0", message = RuralPropertyMessages.LONGITUDE_RANGE_INVALID)
        @DecimalMax(value = "180.0", message = RuralPropertyMessages.LONGITUDE_RANGE_INVALID)
        Double longitude,

        @Positive(message = RuralPropertyMessages.AREA_POSITIVE_REQUIRED)
        Double areaHectares
) {}