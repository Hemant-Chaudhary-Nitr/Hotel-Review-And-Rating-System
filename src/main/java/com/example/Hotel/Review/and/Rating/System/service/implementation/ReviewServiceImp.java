package com.example.Hotel.Review.and.Rating.System.service.implementation;

import com.example.Hotel.Review.and.Rating.System.exception.ResourceNotFoundException;
import com.example.Hotel.Review.and.Rating.System.model.Review;
import com.example.Hotel.Review.and.Rating.System.repository.ReviewRepo;
import com.example.Hotel.Review.and.Rating.System.service.ReviewServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImp implements ReviewServices{
    @Autowired
   private ReviewRepo reviewRepo;

    @Override
    public List<Review> getAllReviewByHotelId(String hotelId){
        List<Review> result= reviewRepo.findByHotelId(hotelId);
        if(result.isEmpty())
            throw new ResourceNotFoundException("Resource not found with ID: " + hotelId);
        return result;
    }

    @Override
    public List<Review> getAllReviewByUserId(String userId) {
        List<Review> result= reviewRepo.findByUserId(userId);
        if(result.isEmpty())
            throw new ResourceNotFoundException("Resource not found with ID: " + userId);
        return result;
    }

    @Override
    @Transactional
    public Review deleteReviewDetails(Integer reviewId) {
           Optional<Review>result= reviewRepo.findById(reviewId);
           Review review = result.orElseThrow(()->new ResourceNotFoundException("Resource not found with ID: " + reviewId));
           reviewRepo.deleteByReviewId(reviewId);
           return review;
    }

    @Override
    public Optional<Review> findByBookingId(String bookingId) {
        return reviewRepo.findByBookingId(bookingId);
    }

    @Override
    public Review saveReviewDetails(Review review) {
        return reviewRepo.save(review);
    }

}
