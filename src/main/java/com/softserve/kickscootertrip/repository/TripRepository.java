package com.softserve.kickscootertrip.repository;

import com.softserve.kickscootertrip.dto.TripStatus;
import com.softserve.kickscootertrip.model.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TripRepository extends JpaRepository<TripEntity, Long> {
    List<TripEntity> findByUserId(UUID id);

    public TripEntity findByScooterIdAndStatus(UUID scooterId, TripStatus tripStatus);

//    @Modifying
//    @Query("update TripEntity t set t.longitude=?1, t.latitude=?2, t.distance=?3 where t.tripId=?4")
//    public TripEntity updatePointDistance(double longitude, double latitude, double distance, UUID tripId);
//
//    @Modifying
//    @Query("update TripEntity t set t.tripFinishes =?1, t.tripTime = ?2, t.status = ?3 where t.tripId  = ?4")
//    public TripEntity updateFinishTriptimeStatus(LocalDateTime finish, Duration tripTime, TripStatus tripStatus, UUID tripId);
}
