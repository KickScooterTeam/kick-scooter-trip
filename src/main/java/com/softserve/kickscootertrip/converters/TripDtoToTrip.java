package com.softserve.kickscootertrip.converters;


import com.softserve.kickscootertrip.dto.TripDto;
import com.softserve.kickscootertrip.model.TripEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TripDtoToTrip implements Converter<TripDto, TripEntity> {
    @Override
    public TripEntity convert(TripDto tripDto) {
        TripEntity entity = new TripEntity();
        entity.setUserId(tripDto.getUserId());
        entity.setScooterId(tripDto.getScooterId());
        entity.setTripStarts(tripDto.getTripStarts());
        entity.setTripFinishes(tripDto.getTripFinishes());
        entity.setTripTime(tripDto.getTripTime());
        return entity;
    }
}
