package com.softserve.kickscootertrip.converters;

import com.softserve.kickscootertrip.model.dto.TripDto;
import com.softserve.kickscootertrip.model.entity.TripEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListDtoToEntity implements Converter<List<TripDto>, List<TripEntity>> {
    @Override
    public List<TripEntity> convert(List<TripDto> tripDtos) {
        List <TripEntity> tripEntities = new ArrayList<>();
        for(TripDto tripDto : tripDtos){
            tripEntities.add(new DtoToEntity().convert(tripDto));
        }
        return tripEntities;

    }
}
