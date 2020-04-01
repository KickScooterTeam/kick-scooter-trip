package com.softserve.kickscootertrip.dto;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Data
public class TripDto {
//    private UUID tripId;
    private UUID userId;
//    private UUID scooterId;
//    private Instant tripStarts;
//    private Instant tripFinishes;
    private Duration tripTime;
//    private double distance;

}
