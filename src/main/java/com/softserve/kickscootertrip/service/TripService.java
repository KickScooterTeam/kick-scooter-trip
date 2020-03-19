package com.softserve.kickscootertrip.service;

import com.softserve.kickscootertrip.model.dto.TripDto;
import com.softserve.kickscootertrip.model.dto.UserDto;
import com.softserve.kickscootertrip.model.entity.TripEntity;
import com.softserve.kickscootertrip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripService {

    private static final double RADIUS_EARTH = 6371008.67;
    private static final String SALES = "TripForSales";
    private static final String PAYMENT = "TripForPayment";
    private final TripRepository tripRepository;
    private ConversionService convert;

    private final KafkaTemplate<String, TripDto> kafkaTemplate;

    public TripDto sendTripToPayment(UUID tripId){
        TripEntity tripEntity = tripRepository.findByTripId(tripId);
        TripDto tripDto = convert.convert(tripEntity, TripDto.class);
        kafkaTemplate.send(PAYMENT, tripDto);
        return tripDto;
    }

    public TripDto findUserTrips(UUID id) {
        TripDto tripDto = new TripDto();
        List<TripEntity> tripEntities = tripRepository.findByUserId(id);
        if (tripEntities.size() != 0) {
            tripDto.setUserId(id);
            int tripsNumber = 0;
            for (TripEntity tripEntity : tripEntities) {
                tripsNumber++;
                tripDto.setTripTime(tripDto.getTripTime() + tripEntity.getTripTime());
                tripDto.setDistance(tripDto.getDistance() + tripEntity.getDistance());
            }
            tripDto.setTripsNumber(tripsNumber);
            kafkaTemplate.send(SALES, tripDto);
        }
        return tripDto;
    }


    @KafkaListener(topics = "b", groupId = "trip")
    public void consumeScooterGeo(UserDto userDto) throws IOException {
        TripEntity tripEntity = tripRepository.findByUserIdAndStatus(userDto.getUserId(), "start");
        double dLat = Math.toRadians(userDto.getPoint().getX() - tripEntity.getPoint().getX());
        double dLon = Math.toRadians(userDto.getPoint().getY() - tripEntity.getPoint().getY());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(tripEntity.getPoint().getX()))
                * Math.cos(Math.toRadians(userDto.getPoint().getX())) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        tripRepository.updatePointDistance(userDto.getPoint(), c * RADIUS_EARTH, tripEntity.getUserId());
    }

    public TripEntity saveStartUserInfo(UserDto userDto) {
        TripEntity tripEntity = new TripEntity();
        tripEntity.setUserId(userDto.getUserId());
        tripEntity.setScooterId(userDto.getScooterId());
        tripEntity.setStart(new Date());
        tripEntity.setStatus(userDto.getStatus());
        return tripRepository.save(tripEntity);
    }


    public TripEntity saveStopUserInfo(UserDto userDto) {
        UUID userId = userDto.getUserId();
        TripEntity tripEntity = tripRepository.findByUserIdAndStatus(userId, "start");
        Date finish = new Date();
        long tripTime = finish.getTime() - tripEntity.getStart().getTime();
        String status = tripEntity.getStatus();
        UUID tripId = tripEntity.getTripId();
        findUserTrips(userId);
        sendTripToPayment(tripId);
        return tripRepository.updateFinishTriptimeStatus(finish, tripTime, status, tripId);
    }
}
