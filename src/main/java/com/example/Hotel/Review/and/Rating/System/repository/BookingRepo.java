package com.example.Hotel.Review.and.Rating.System.repository;

import com.example.Hotel.Review.and.Rating.System.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking,String> {
    boolean existsByUserIdAndHotelIdAndBookingId(String userId,String hotelId,String bookingId);
}
