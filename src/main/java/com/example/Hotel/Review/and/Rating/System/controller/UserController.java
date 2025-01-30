package com.example.Hotel.Review.and.Rating.System.controller;

import com.example.Hotel.Review.and.Rating.System.authentication.LoginResponse;
import com.example.Hotel.Review.and.Rating.System.exception.ResourceNotFoundException;
import com.example.Hotel.Review.and.Rating.System.model.User;
import com.example.Hotel.Review.and.Rating.System.service.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServices userServices;

    @PostMapping()
    public User saveUser(@RequestBody User user){
//        Optional<User> result=userServices.findUser(user.getId());
        logger.info("save user details function executed");
        return userServices.saveUser(user);
    }

    @PostMapping("/auth")
    public ResponseEntity<LoginResponse> authUser(@RequestBody User user){
        logger.info("user authentication function executed for userId: "+user.getUserId());
        LoginResponse loginResponse= userServices.authUser(user);
        return ResponseEntity.ok(loginResponse);
    }
}
