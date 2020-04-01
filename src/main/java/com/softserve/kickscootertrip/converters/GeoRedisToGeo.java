package com.softserve.kickscootertrip.converters;

import com.softserve.kickscootertrip.dto.TripDto;
import com.softserve.kickscootertrip.model.Geo;
import com.softserve.kickscootertrip.model.GeoRedis;
import com.softserve.kickscootertrip.model.TripEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GeoRedisToGeo implements Converter<GeoRedis, Geo>{
    @Override
    public Geo convert(GeoRedis geoRedis) {
            Geo geo = new Geo();
            geo.setScooterId(geoRedis.getScooterId());
            geo.setPoint(geoRedis.getPoint());
        return geo;
    }
}
