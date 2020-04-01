package com.softserve.kickscootertrip.controller;

import com.softserve.kickscootertrip.dto.ScooterStatusDto;
import com.softserve.kickscootertrip.service.GeoServise;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class KafkaController {
    private final GeoServise geoServise;
    private static final String IN_USE = "in-use-scooter-data";


    @KafkaListener(topics = IN_USE, groupId = "trip")
    public void consumer(ScooterStatusDto scooterStatusDto){
        geoServise.save(scooterStatusDto);

    }
}
