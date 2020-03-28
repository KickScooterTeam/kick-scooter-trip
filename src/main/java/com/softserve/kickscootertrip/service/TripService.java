package com.softserve.kickscootertrip.service;

//import com.softserve.kickscootertrip.controller.KafkaController;

import com.softserve.kickscootertrip.controller.KafkaController;
import com.softserve.kickscootertrip.dto.TripDto;
import com.softserve.kickscootertrip.dto.TripStatus;
import com.softserve.kickscootertrip.dto.UIDto;
import com.softserve.kickscootertrip.model.TripEntity;
import com.softserve.kickscootertrip.repository.TripRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

//import org.springframework.kafka.annotation.KafkaListener;

@Service
@RequiredArgsConstructor
public class TripService {

    private static final double RADIUS_EARTH = 6371008.67;
    private final TripRepository tripRepository;
    private ConversionService conversionService;
    private final KafkaController kafkaController;
    private final GeoServise geoServise;


    public TripDto sendTripToPayment(TripEntity tripEntity) {
        TripDto tripDto = conversionService.convert(tripEntity, TripDto.class);
        kafkaController.toPayment(tripDto);
        return tripDto;
    }

//    public TripDto findUserTrips(UUID id) {
//        TripDto tripDto = new TripDto();
//        List<TripEntity> tripEntities = tripRepository.findByUserId(id);
//        if (tripEntities.size() != 0) {
//            tripDto.setUserId(id);
//            for (TripEntity tripEntity : tripEntities) {
////                tripDto.setTripTime(tripDto.getTripTime() + tripEntity.getTripTime());
//                tripDto.setDistance(tripDto.getDistance() + tripEntity.getDistance());
//            }
//            tripDto.setTripsNumber(tripEntities.size());
//            kafkaController.toSales(tripDto);
//        }
//        return tripDto;
//    }



    public TripEntity saveStartUserInfo(UIDto UIDto) {
        TripEntity tripEntity = new TripEntity();
        tripEntity.setUserId(UIDto.getUserId());
        tripEntity.setScooterId(UIDto.getScooterId());
        tripEntity.setTripStarts(Instant.now());
        tripEntity.setStatus(TripStatus.ON_RIDE);
        return tripRepository.save(tripEntity);
    }


    public UUID saveStopUserInfo(UIDto UIDto) {
        UUID scooterId = UIDto.getScooterId();
        TripEntity tripEntity = tripRepository.findByScooterIdAndStatus(scooterId, TripStatus.ON_RIDE);
        System.out.println(tripEntity);
        tripEntity.setTripFinishes(Instant.now());
        Duration tripTime = Duration.between(tripEntity.getTripStarts(), Instant.now());
        tripEntity.setTripTime(tripTime);
        tripEntity.setStatus(TripStatus.FREE);
        tripEntity.setDistance(geoServise.calculateDistace(UIDto.getScooterId()));
        tripRepository.save(tripEntity);

        sendTripToPayment(tripEntity);
        return tripEntity.getTripId();
    }
}
