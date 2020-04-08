package com.softserve.kickscootertrip.controller;


import com.softserve.kickscootertrip.dto.TripDto;
import com.softserve.kickscootertrip.dto.UIDto;
import com.softserve.kickscootertrip.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
@Slf4j
public class TripController {

    private final TripService tripService;
    private final PaymentClient paymentClient;
    private final VehicleClient vehicleClient;

    @Value("${service-token}")
    private String bearerToken;

    @PostMapping("/start")
    public ResponseEntity<UUID> setStartUserInfo(@RequestBody UIDto uiDto) {
        if (!paymentClient.isUserCanPay(bearerToken, uiDto.getUserId())) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).build();
        }
        vehicleClient.acquireScooter(bearerToken, uiDto.getScooterId());
        return ResponseEntity.ok(tripService.saveStartUserInfo(uiDto).getTripId());
    }

    @PostMapping("/finish")
    public ResponseEntity<UUID> setFinishUserInfo(@RequestBody UIDto uiDto) {
        vehicleClient.freeScooter(bearerToken, uiDto.getScooterId());
        TripDto tripDto = tripService.saveStopUserInfo(uiDto);
        log.info("TripDto for sending " + tripDto);
        paymentClient.createInvoice(bearerToken, tripDto);
        return ResponseEntity.ok(tripDto.getUserId());
    }

}
