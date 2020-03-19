package com.softserve.kickscootertrip.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class TripDto {
    private UUID tripId;
    private UUID userId;
    private UUID scooterId;
    private Date start;
    private Date finish;
    private long tripTime;
    private double distance;
    private int tripsNumber;
    private String status;

}
