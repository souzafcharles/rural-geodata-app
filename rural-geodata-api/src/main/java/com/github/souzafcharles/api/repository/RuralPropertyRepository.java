package com.github.souzafcharles.api.repository;

import com.github.souzafcharles.api.model.entities.RuralProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuralPropertyRepository  extends JpaRepository<RuralProperty, Long> {
}
