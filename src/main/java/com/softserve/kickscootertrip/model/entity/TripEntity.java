package com.softserve.kickscootertrip.model.entity;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "trip")
public class TripEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "tripId")
    private UUID tripId;
    @Column(name = "userId")
    private UUID userId;
    @Column(name = "scooterId")
    private UUID scooterId;
    @Column(name = "start")
    private LocalDateTime start;
    @Column(name = "finish")
    private LocalDateTime finish;
    @Column(name = "tripTime")
    private long tripTime;
    @Column(name = "point")
    private Point point;
    @Column(name = "distance")
    private double distance;
    @Column(name = "status")
    private String status;

}
