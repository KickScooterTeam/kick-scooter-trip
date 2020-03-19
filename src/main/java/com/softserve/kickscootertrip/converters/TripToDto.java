package com.softserve.kickscootertrip.converters;

import com.softserve.kickscootertrip.model.dto.TripDto;
import com.softserve.kickscootertrip.model.entity.TripEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TripToDto implements Converter<TripEntity, TripDto> {

    @Override
    public TripDto convert(TripEntity tripEntity) {
        TripDto tripDto = new TripDto();
        tripDto.setUserId(tripEntity.getUserId());
        tripDto.setScooterId(tripEntity.getScooterId());
        tripDto.setStart(tripEntity.getStart());
        tripDto.setFinish(tripEntity.getFinish());
        tripDto.setTripTime(tripEntity.getTripTime());
        return tripDto;
    }
}
