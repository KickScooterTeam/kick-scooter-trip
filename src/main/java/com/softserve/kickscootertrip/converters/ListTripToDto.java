package com.softserve.kickscootertrip.converters;

import com.softserve.kickscootertrip.model.dto.TripDto;
import com.softserve.kickscootertrip.model.entity.TripEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListTripToDto implements Converter<List<TripEntity>, List<TripDto>> {

    @Override
    public List<TripDto> convert(List<TripEntity> tripEntities) {
        List<TripDto> tripDtos = new ArrayList<>();
        for(TripEntity tripEntity : tripEntities){
            tripDtos.add(new TripToDto().convert(tripEntity));
        }
        return tripDtos;
    }
}
