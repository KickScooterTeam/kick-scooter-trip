package com.softserve.kickscootertrip.model.dto;

import lombok.Data;
import org.springframework.data.geo.Point;

import java.util.UUID;

@Data
public class UserDto {
    private UUID userId;
    private UUID scooterId;
    private Point point;
    private String status;

}
