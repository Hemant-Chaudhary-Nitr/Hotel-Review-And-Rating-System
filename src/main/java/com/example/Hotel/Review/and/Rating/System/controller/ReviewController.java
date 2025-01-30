package com.example.Hotel.Review.and.Rating.System.controller;

import com.example.Hotel.Review.and.Rating.System.model.Review;
import com.example.Hotel.Review.and.Rating.System.service.CommentServices;
import com.example.Hotel.Review.and.Rating.System.service.HotelServices;
import com.example.Hotel.Review.and.Rating.System.service.ReviewServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/review")
public class ReviewController {
    Logger logger= LoggerFactory.getLogger(ReviewController.class);
    @Autowired
    private ReviewServices reviewServices;

    @Autowired
    private CommentServices commentServices;

    @Autowired
    private HotelServices hotelServices;

    @GetMapping("/userReview/{userId}")
    public ResponseEntity<List<Review>> getAllReviewByUserId(@PathVariable String userId) {
        logger.info("review fetch function executed correspond to userId: "+userId);
        List<Review>result= reviewServices.getAllReviewByUserId(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/hotelReview/{hotelId}")
    public ResponseEntity<List<Review>> getAllReviewByHotelId(@PathVariable String hotelId) {
        logger.info("review fetch function executed correspond to hotelId: "+hotelId);
        List<Review>result= reviewServices.getAllReviewByHotelId(hotelId);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public Review saveReviewDetails(@RequestBody Review review) {
        logger.info("save review details function executed");
        Review review1=reviewServices.saveReviewDetails(review);
        double averageRating = hotelServices.calculateAverageRatingAndUpdateHotel(review.getHotelId());
        return review1;
    }


    @DeleteMapping("/deleteReview/{reviewId}")
    public ResponseEntity<Review> deleteReviewDetails(@PathVariable Integer reviewId) {
            logger.info("review delete function executed correspond to reviewId: "+reviewId);
            Review review=reviewServices.deleteReviewDetails(reviewId);
            commentServices.deleteCommentDetails(reviewId);
            double averageRating = hotelServices.calculateAverageRatingAndUpdateHotel(review.getHotelId());
            return ResponseEntity.ok(review);
    }
}