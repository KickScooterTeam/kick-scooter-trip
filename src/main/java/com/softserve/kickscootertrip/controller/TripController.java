package com.softserve.kickscootertrip.controller;


import com.softserve.kickscootertrip.dto.UIDto;
import com.softserve.kickscootertrip.service.TripService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/user/start")
    public ResponseEntity<UUID> setStartUserInfo(@RequestBody UIDto UIDto){
        return ResponseEntity.ok(tripService.saveStartUserInfo(UIDto).getTripId());
    }

    @PostMapping("/user/end")
    public ResponseEntity<UUID> setFinishUserInfo(@RequestBody UIDto UIDto){
        return ResponseEntity.ok(tripService.saveStopUserInfo(UIDto));
    }

}
