package com.example.Hotel.Review.and.Rating.System.service.implementation;

import com.example.Hotel.Review.and.Rating.System.exception.ResourceNotFoundException;
import com.example.Hotel.Review.and.Rating.System.model.Comment;
import com.example.Hotel.Review.and.Rating.System.repository.CommentRepo;
import com.example.Hotel.Review.and.Rating.System.service.CommentServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CommentServiceImp implements CommentServices {
    @Autowired
    private CommentRepo commentRepo;

    @Override
    public Comment getCommentDetails(Integer reviewId){
        Optional<Comment> comment= commentRepo.findByReviewId(reviewId);
        Comment result= comment.orElseThrow(()->new ResourceNotFoundException("Resource not found with id : " + reviewId));
        return result;
    }
    @Override
    public Comment saveCommentDetails(Comment comment){
        return commentRepo.save(comment);
    }

    @Override
    @Transactional
    public void deleteCommentDetails(Integer reviewId){
        Optional<Comment> result= commentRepo.findByReviewId(reviewId);
       if(!result.isEmpty()) commentRepo.deleteByReviewId(reviewId);
    }
}
