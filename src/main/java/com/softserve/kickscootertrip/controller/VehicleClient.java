package com.softserve.kickscootertrip.controller;

import com.softserve.kickscootertrip.dto.UiPointDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(name = "vehicle-service")
public interface VehicleClient {

    @PutMapping(path = "/scooters/status/{scooterId}/acquire")
    String acquireScooter(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken, @PathVariable UUID scooterId);

    @PutMapping(path = "/scooters/status/{scooterId}/free")
    String freeScooter(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken, @PathVariable UUID scooterId);

    @GetMapping(path = "/{id}/details")
    UiPointDto getScooterStatusDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken, @PathVariable UUID id);

}
