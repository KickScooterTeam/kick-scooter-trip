package com.softserve.kickscootertrip.repository;

import com.softserve.kickscootertrip.model.Geo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GeoRepository extends JpaRepository<Geo, UUID> {
}
