package com.softserve.kickscootertrip.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "vehicle-client", url = "${vehicle-url}")
public interface VehicleClient {

    @PutMapping(path = "/scooters/status/acquire/{scooterId}")
    String acquireScooter(@PathVariable UUID scooterId);

    @PutMapping(path = "/scooters/status/free/{scooterId}")
    String freeScooter(@PathVariable UUID scooterId);

}
