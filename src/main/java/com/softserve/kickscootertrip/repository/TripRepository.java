package com.softserve.kickscootertrip.repository;

import com.softserve.kickscootertrip.dto.TripStatus;
import com.softserve.kickscootertrip.model.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TripRepository extends JpaRepository<TripEntity, Long> {
    List<TripEntity> findByUserId(UUID id);

    TripEntity findByScooterIdAndStatus(UUID scooterId, TripStatus tripStatus);
    Optional<TripEntity> findByUserIdAndStatus(UUID userId, TripStatus tripStatus);
}
