package com.example.Hotel.Review.and.Rating.System.service.implementation;

import com.example.Hotel.Review.and.Rating.System.exception.ResourceNotFoundException;
import com.example.Hotel.Review.and.Rating.System.model.Hotel;
import com.example.Hotel.Review.and.Rating.System.model.Review;
import com.example.Hotel.Review.and.Rating.System.repository.HotelRepo;
import com.example.Hotel.Review.and.Rating.System.repository.ReviewRepo;
import com.example.Hotel.Review.and.Rating.System.service.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImp implements HotelServices {
    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    public Hotel findHotel(String id) {
        Optional<Hotel> hotel= hotelRepo.findById(id);
        Hotel result= hotel.orElseThrow(()->new ResourceNotFoundException("Resource not found with id : " + id));
        return result;
    }

    @Override
    public Hotel saveHotel(Hotel emp) {
        return hotelRepo.save(emp);
    }


    @Override
    public double calculateAverageRatingAndUpdateHotel(String hotelId) {
        List<Review> reviews = reviewRepo.findByHotelId(hotelId);
        double totalRating = reviews.stream().mapToDouble(Review::getRating).sum();
        int numberOfReviews = reviews.size();
        double averageRating = numberOfReviews > 0 ? (double) totalRating / numberOfReviews : 0.0;

        // Update average rating in the hotel table
        Hotel hotel = hotelRepo.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found for ID: " + hotelId));
        hotelRepo.updateAverageRatingById(hotelId, averageRating);
        return averageRating;
    }
}
