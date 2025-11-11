package com.github.souzafcharles.api.model.entities;

import com.github.souzafcharles.api.util.RuralPropertyMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

@Entity
@Table(name = "rural_properties")
public class RuralProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property_name", nullable = false)
    @NotBlank(message = RuralPropertyMessages.PROPERTY_NAME_REQUIRED)
    @Size(max = 255, message = RuralPropertyMessages.PROPERTY_NAME_SIZE_EXCEEDED)
    private String name;

    @Column(name = "latitude")
    @DecimalMin(value = "-90.0", message = RuralPropertyMessages.LATITUDE_RANGE_INVALID)
    @DecimalMax(value = "90.0", message = RuralPropertyMessages.LATITUDE_RANGE_INVALID)
    private Double latitude;

    @Column(name = "longitude")
    @DecimalMin(value = "-180.0", message = RuralPropertyMessages.LONGITUDE_RANGE_INVALID)
    @DecimalMax(value = "180.0", message = RuralPropertyMessages.LONGITUDE_RANGE_INVALID)
    private Double longitude;

    @Column(name = "area_hectares")
    @Positive(message = RuralPropertyMessages.AREA_POSITIVE_REQUIRED)
    private Double areaHectares;

    public RuralProperty() {}

    public RuralProperty(String name, Double latitude, Double longitude, Double areaHectares) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.areaHectares = areaHectares;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAreaHectares() {
        return areaHectares;
    }

    public void setAreaHectares(Double areaHectares) {
        this.areaHectares = areaHectares;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RuralProperty that = (RuralProperty) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude);
    }

}