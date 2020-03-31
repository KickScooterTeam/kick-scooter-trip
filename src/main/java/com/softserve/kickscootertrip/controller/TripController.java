package com.softserve.kickscootertrip.controller;


import com.softserve.kickscootertrip.dto.TripDto;
import com.softserve.kickscootertrip.dto.UIDto;
import com.softserve.kickscootertrip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;
    private final PaymentClient paymentClient;
    private final VehicleClient vehicleClient;


    @PostMapping("/trip/start")
    public ResponseEntity<UUID> setStartUserInfo(@RequestBody UIDto uiDto) {
        if (!paymentClient.isUserCanPay(uiDto.getUserId())) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).build();
        }
        vehicleClient.acquireScooter(uiDto.getScooterId());
        return ResponseEntity.ok(tripService.saveStartUserInfo(uiDto).getTripId());
    }

    @PostMapping("/trip/finish")
    public ResponseEntity<UUID> setFinishUserInfo(@RequestBody UIDto uiDto) {
        vehicleClient.freeScooter(uiDto.getScooterId());
        TripDto tripDto = tripService.saveStopUserInfo(uiDto);
        paymentClient.createInvoice(tripDto);
        return ResponseEntity.ok(tripDto.getUserId());
    }

}
