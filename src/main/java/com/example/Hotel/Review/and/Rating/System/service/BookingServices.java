package com.example.Hotel.Review.and.Rating.System.service;

import com.example.Hotel.Review.and.Rating.System.model.Booking;


public interface BookingServices {
    boolean getBookingDetails(String userId,String hotelId, String bookingId);
    Booking saveBookingDetails(Booking arg);
}
