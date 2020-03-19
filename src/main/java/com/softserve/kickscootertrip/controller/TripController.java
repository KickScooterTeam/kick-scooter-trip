package com.softserve.kickscootertrip.controller;


import com.softserve.kickscootertrip.model.dto.UserDto;
import com.softserve.kickscootertrip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping("/user/start")
    public ResponseEntity setStartUserInfo(@RequestBody UserDto userDto){
        return ResponseEntity.ok(tripService.saveStartUserInfo(userDto));
    }

    @PostMapping("/user/end")
    public ResponseEntity setFinishUserInfo(@RequestBody UserDto userDto){
        return ResponseEntity.ok(tripService.saveStopUserInfo(userDto));
    }

}
