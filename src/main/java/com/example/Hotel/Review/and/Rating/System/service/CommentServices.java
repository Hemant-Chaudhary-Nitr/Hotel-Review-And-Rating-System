package com.example.Hotel.Review.and.Rating.System.service;

import com.example.Hotel.Review.and.Rating.System.model.Comment;

import java.util.Optional;

public interface CommentServices {
       Comment getCommentDetails(Integer reviewId);
       Comment saveCommentDetails(Comment comment);

       void deleteCommentDetails(Integer reviewId);
}
