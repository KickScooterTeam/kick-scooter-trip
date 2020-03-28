package com.softserve.kickscootertrip.repository;

import com.softserve.kickscootertrip.dto.Point;
import com.softserve.kickscootertrip.model.Geo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GeoRepository extends CrudRepository<Geo, String> {
    List<Geo> findByScooterId(UUID scooterId);
    Optional<Geo> findById(String id);

//    void deleteByScooterId(UUID scooterId);
}
