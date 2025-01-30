package com.example.Hotel.Review.and.Rating.System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Reviews")

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Review_id")
    private Integer reviewId;

    @Column(name="User_id")
    private String userId;

    @Column(name="Hotel_id")
    private String hotelId;

    @Column(name="Rating")
    private double rating;

    @Column(name="Review")
    private String review;

    @Column (name="booking_id")
    private String bookingId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastUpdate;

    @PrePersist
    private void onCreate(){
        lastUpdate=new Date();
    }

}
