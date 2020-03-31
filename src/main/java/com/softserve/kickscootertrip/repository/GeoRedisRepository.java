package com.softserve.kickscootertrip.repository;

import com.softserve.kickscootertrip.model.GeoRedis;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface GeoRedisRepository extends CrudRepository<GeoRedis, String> {
    List<GeoRedis> findByScooterId(UUID scooterId);

}
