package com.softserve.kickscootertrip.converters;


import com.softserve.kickscootertrip.model.dto.TripDto;
import com.softserve.kickscootertrip.model.entity.TripEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToEntity implements Converter<TripDto, TripEntity> {
    @Override
    public TripEntity convert(TripDto tripDto) {
        TripEntity entity = new TripEntity();
        entity.setUserId(tripDto.getUserId());
        entity.setScooterId(tripDto.getScooterId());
        entity.setStart(tripDto.getStart());
        entity.setFinish(tripDto.getFinish());
        entity.setTripTime(tripDto.getTripTime());
        return entity;
    }
}
