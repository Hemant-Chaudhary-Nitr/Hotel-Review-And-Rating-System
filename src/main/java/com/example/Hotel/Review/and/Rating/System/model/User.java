package com.example.Hotel.Review.and.Rating.System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person", indexes = @Index(name = "idx_userId_tag", columnList = "Id,Tag"))

public class User {
    //      @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "Tag")
    private String tag;
}
