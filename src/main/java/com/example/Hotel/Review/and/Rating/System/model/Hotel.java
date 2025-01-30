package com.example.Hotel.Review.and.Rating.System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="hotel",indexes = @Index(name="idx_hotelId_name",columnList = "hotel_id,hotel_name"))

public class Hotel {
    @Id
    @Column(name="hotel_id")
    private String hotelId;
    @Column(name="hotel_name")
    private String hotelName;
    @Column(name="owner_name")
    private String ownerName;
    @Column(name="owner_contact")
    private String ownerContact;
    @Column(name="avg_rating")
    private double avgRating;
    @Column(name="hotel_details")
    private String description;

}
