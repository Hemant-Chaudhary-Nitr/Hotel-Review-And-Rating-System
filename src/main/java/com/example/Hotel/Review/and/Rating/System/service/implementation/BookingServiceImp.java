package com.example.Hotel.Review.and.Rating.System.service.implementation;

import com.example.Hotel.Review.and.Rating.System.model.Booking;
import com.example.Hotel.Review.and.Rating.System.repository.BookingRepo;
import com.example.Hotel.Review.and.Rating.System.service.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImp implements BookingServices {
    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public boolean getBookingDetails(String userId,String hotelId,String bookingId){
           return bookingRepo.existsByUserIdAndHotelIdAndBookingId(userId,hotelId,bookingId);
    }

    @Override
    public Booking saveBookingDetails(Booking arg){
        return bookingRepo.save(arg);
    }
}
