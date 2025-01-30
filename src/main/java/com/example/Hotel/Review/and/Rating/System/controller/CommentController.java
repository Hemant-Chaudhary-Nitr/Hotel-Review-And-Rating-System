package com.example.Hotel.Review.and.Rating.System.controller;

import com.example.Hotel.Review.and.Rating.System.model.Comment;
import com.example.Hotel.Review.and.Rating.System.service.CommentServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    Logger logger= LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private CommentServices commentServices;

    @PostMapping()
    public Comment saveCommentDetails(@RequestBody Comment comment){
        logger.info("save comment details function executed");
        return commentServices.saveCommentDetails(comment);
    }

    @DeleteMapping("/deleteComment/{id}")
    public void deleteCommentDetails(@PathVariable Integer id){
            logger.info("Comment delete function executed correspond to reviewId: " + id);
            commentServices.deleteCommentDetails(id);
    }

    @GetMapping("/getComment/{id}")
    public ResponseEntity<Comment> getCommentDetails(@PathVariable Integer id){
        logger.info("Comment fetch function executed correspond to reviewId: " + id);
        Comment comment= commentServices.getCommentDetails(id);
        return ResponseEntity.ok(comment);
    }
}
