package com.example.Hotel.Review.and.Rating.System.service;

import com.example.Hotel.Review.and.Rating.System.authentication.LoginResponse;
import com.example.Hotel.Review.and.Rating.System.model.User;


public interface UserServices {
    User saveUser(User emp);

    LoginResponse authUser(User user);
}
