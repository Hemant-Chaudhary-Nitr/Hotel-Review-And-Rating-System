package com.example.Hotel.Review.and.Rating.System.controller;

import com.example.Hotel.Review.and.Rating.System.exception.ResourceAlreadyExistException;
import com.example.Hotel.Review.and.Rating.System.exception.ResourceNotFoundException;
import com.example.Hotel.Review.and.Rating.System.model.Booking;
import com.example.Hotel.Review.and.Rating.System.model.Review;
import com.example.Hotel.Review.and.Rating.System.service.BookingServices;
import com.example.Hotel.Review.and.Rating.System.service.ReviewServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/booking")
public class BookingController {
    Logger logger = LoggerFactory.getLogger(BookingController.class);
    @Autowired
    private BookingServices bookingServices;

    @Autowired
    private ReviewServices reviewServices;

    @PostMapping()
    public Booking saveDetails(@RequestBody Booking booking) {
        logger.info("save Booking Details function executed");
        return bookingServices.saveBookingDetails(booking);
    }

    @GetMapping("/exists")
    public Boolean checkBookingExists(
            @RequestParam String userId,
            @RequestParam String hotelId,
            @RequestParam String bookingId
    ) {
        logger.info("Validation of the bookingId correspond to a particular userId and hotelId executed");
        boolean variable = bookingServices.getBookingDetails(userId, hotelId, bookingId);
        Optional<Review> review = reviewServices.findByBookingId(bookingId);
        if (review.isPresent() && variable)
            throw new ResourceAlreadyExistException("Resource already exist with booking id : " + bookingId);
        return variable;
    }
}
