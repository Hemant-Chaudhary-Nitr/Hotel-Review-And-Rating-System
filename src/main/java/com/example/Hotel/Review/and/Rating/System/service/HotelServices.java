package com.example.Hotel.Review.and.Rating.System.service;

import com.example.Hotel.Review.and.Rating.System.model.Hotel;

import java.util.Optional;

public interface HotelServices {
    Hotel findHotel(String id);

    Hotel saveHotel(Hotel hotel);

    double calculateAverageRatingAndUpdateHotel(String hotelId);

}
