package com.softserve.kickscootertrip.service;


import com.softserve.kickscootertrip.dto.Point;
import com.softserve.kickscootertrip.dto.ScooterStatusDto;
import com.softserve.kickscootertrip.model.Geo;
import com.softserve.kickscootertrip.repository.GeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class GeoServise {
    private final GeoRepository geoRepository;

    public Geo save(ScooterStatusDto scooterStatusDto) {
        Geo geo = new Geo();
        geo.setScooterId(scooterStatusDto.getId());
        geo.setPoint(scooterStatusDto.getGpsPoint());
        geoRepository.save(geo);
        return geo;
    }

    public double calculateDistace(UUID scooterId) {
        double distance = 0;
        List<Geo> geoTrip = geoRepository.findByScooterId(scooterId);
        Point previousPoint = geoTrip.get(0).getPoint();
        for (Geo geo : geoTrip) {
            distance += betweenTwoPoints(previousPoint, geo.getPoint());
            previousPoint = geo.getPoint();
            geoRepository.delete(geo);
        }
        return distance;
    }

    private double betweenTwoPoints(Point previous, Point next) {
        double R = 6378.137; // Radius of Earth in KM
        double dLat = next.getY() * Math.PI / 180 - previous.getY() * Math.PI / 180;
        double dLon = next.getX() * Math.PI / 180 - previous.getX() * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(previous.getY() * Math.PI / 180) * Math.cos(next.getY() * Math.PI / 180) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d * 1000; // meters
    }
}
