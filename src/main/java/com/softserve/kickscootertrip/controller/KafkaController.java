package com.softserve.kickscootertrip.controller;

import com.softserve.kickscootertrip.dto.ScooterStatusDto;
import com.softserve.kickscootertrip.dto.TripDto;
import com.softserve.kickscootertrip.model.Geo;
import com.softserve.kickscootertrip.repository.GeoRepository;
import com.softserve.kickscootertrip.service.GeoServise;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class KafkaController {
    private final GeoRepository geoRepository;
    private final KafkaTemplate<String, TripDto> kafkaTemplate;
    private final GeoServise geoServise;
    private static final String RAW_DATA = "${kafka.topic.active-scooters}";

    public void toPayment(TripDto tripDto){
        kafkaTemplate.send("${kafka.topic.payment}", tripDto);
    }

    public void toSales(TripDto tripDto){
        kafkaTemplate.send("${kafka.topic.sales}", tripDto);
    }

    @KafkaListener(topics = RAW_DATA, groupId = "trip") //todo put into app.yml
    public void consumer(ScooterStatusDto scooterStatusDto){
        geoServise.save(scooterStatusDto);

    }




}
