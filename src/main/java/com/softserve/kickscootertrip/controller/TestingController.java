package com.softserve.kickscootertrip.controller;

import com.softserve.kickscootertrip.model.Geo;
//import com.softserve.kickscootertrip.repository.GeoRepository;
//import com.softserve.kickscootertrip.service.GeoServise;
import com.softserve.kickscootertrip.repository.GeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/geo")
@RequiredArgsConstructor
public class TestingController {

//    private final GeoServise geoServise;
    private final GeoRepository geoRepository;



//    @GetMapping("/{tripId}")
//    public List<Geo> getTripGeo(@PathVariable("id") UUID id){
//        return geoServise.findAllByTripId(id);
//    }

    @GetMapping("/{p1}/{p2}/{p3}/{p4}")
    public Iterable<Geo>  add(/*@PathVariable double p1,
                              @PathVariable double p2,
                              @PathVariable double p3,
                              @PathVariable double p4*/){
        UUID u1 = UUID.fromString("2a7023ad-55b5-4ca2-a71c-f5708860b0bc");
        UUID u2 = UUID.fromString("82d7a5c5-2596-4686-bfa0-425480e24a12");

        Point point1= new Point(2,3);
        Point point2= new Point(4,5);

        Geo geo1 = new Geo();
        geo1.setScooterId(u1);
   //   geo1.setPoint(point1);
    //    geo1.setId(3);
        geoRepository.save(geo1);
        Geo geo2 = new Geo();
        geo2.setScooterId(u2);
//        geo2.setId(5);
     //   geo2.setPoint(point2);
         geoRepository.save(geo2);

        return geoRepository.findAll();
    }

    @GetMapping("/add")
    public Iterable<Geo> af(){
        Point point= new Point(1,2);
   //     Geo geo = new Geo(1, UUID.randomUUID(), point);
    //    geoRepository.save(geo);
        return geoRepository.findAll();
    }


}
