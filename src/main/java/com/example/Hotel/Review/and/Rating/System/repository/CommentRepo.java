package com.example.Hotel.Review.and.Rating.System.repository;

import com.example.Hotel.Review.and.Rating.System.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {
    void deleteByReviewId(Integer id);
    Optional<Comment> findByReviewId(Integer id);
}
