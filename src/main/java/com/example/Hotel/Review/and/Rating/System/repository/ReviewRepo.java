package com.example.Hotel.Review.and.Rating.System.repository;

import com.example.Hotel.Review.and.Rating.System.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Integer> {
     List<Review> findByHotelId(String hotelId);
     List<Review> findByUserId(String userId);

     void deleteByReviewId(Integer reviewId);

    Optional<Review> findByBookingId(String bookingId);
}
