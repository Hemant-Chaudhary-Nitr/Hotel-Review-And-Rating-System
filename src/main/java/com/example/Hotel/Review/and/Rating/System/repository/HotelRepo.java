package com.example.Hotel.Review.and.Rating.System.repository;

import com.example.Hotel.Review.and.Rating.System.model.Hotel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<Hotel,String> {
    @Modifying
    @Transactional
    @Query("UPDATE Hotel h SET h.avgRating = :averageRating WHERE h.hotelId = :hotelId")
    void updateAverageRatingById(String hotelId, double averageRating);
}
