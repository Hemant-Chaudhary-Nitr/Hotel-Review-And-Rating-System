package com.example.Hotel.Review.and.Rating.System.controller;

import com.example.Hotel.Review.and.Rating.System.model.Hotel;
import com.example.Hotel.Review.and.Rating.System.service.HotelServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/hotel")
public class HotelController {
    Logger logger= LoggerFactory.getLogger(HotelController.class);
    @Autowired
    private HotelServices hotelServices;

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> findHotel(@PathVariable String id){
        logger.info("hotel info fetch function executed correspond to hotelId: "+id);
        Hotel hotel= hotelServices.findHotel(id);
        return ResponseEntity.ok(hotel);
    }

    @PostMapping
    public Hotel saveHotel(@RequestBody Hotel hotel){
        logger.info("save hotel details function executed");
        return hotelServices.saveHotel(hotel);
    }
}
