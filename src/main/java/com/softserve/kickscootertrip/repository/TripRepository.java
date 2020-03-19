package com.softserve.kickscootertrip.repository;

import com.softserve.kickscootertrip.model.entity.TripEntity;
import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TripRepository extends JpaRepository<TripEntity, Long> {
List<TripEntity> findByUserId(UUID id);
    public TripEntity findByUserIdAndStatus(UUID userId, String status);
    public TripEntity findByTripId(UUID tripId);


    @Modifying
    @Query(value = "update trip t set t.point=?1, t.distance = ?2 where t.trip_id = ?3", nativeQuery = true)
    public TripEntity updatePointDistance(Point point, double distance, UUID tripId);

    @Modifying
    @Query(value = "update trip t set t.finish =?1, t.trip_time = ?2, t.status = ?3 where t.trip_id = ?4", nativeQuery = true)
    public TripEntity updateFinishTriptimeStatus(LocalDateTime finish, Long tripTime, String status, UUID tripId);
}
