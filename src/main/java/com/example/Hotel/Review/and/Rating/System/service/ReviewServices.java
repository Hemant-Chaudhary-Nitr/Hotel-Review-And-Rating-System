package com.example.Hotel.Review.and.Rating.System.service;

import com.example.Hotel.Review.and.Rating.System.model.Review;

import java.util.List;
import java.util.Optional;


public interface ReviewServices {
    List<Review> getAllReviewByHotelId(String hotelId);
    List<Review> getAllReviewByUserId(String userId);

    Review deleteReviewDetails(Integer reviewId);

    Optional<Review> findByBookingId(String bookingId);
    Review saveReviewDetails(Review review);
}
