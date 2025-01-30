package com.example.Hotel.Review.and.Rating.System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Booking")

public class Booking {
    @Id
    @Column(name="booking_id")
    private String  bookingId;

    @Column(name="user_id")
    private String userId;

    @Column(name="hotel_id")
    private String hotelId;
}
