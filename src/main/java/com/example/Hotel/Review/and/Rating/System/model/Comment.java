package com.example.Hotel.Review.and.Rating.System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Comment_id")
    private Integer commentId;

    @Column(name="review_id")
    private Integer reviewId;

    @Column(name="user_id")
    private String userId;

    @Column(name="comment")
    private String reply;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastUpdate;

    @PrePersist
    private void onCreate(){
        lastUpdate=new Date();
    }
}
