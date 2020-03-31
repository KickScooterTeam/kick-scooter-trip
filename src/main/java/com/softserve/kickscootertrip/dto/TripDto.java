package com.softserve.kickscootertrip.dto;

import lombok.Data;

import java.time.Duration;
import java.util.UUID;

@Data
public class TripDto {
    private UUID userId;
    private Duration duration;

}
