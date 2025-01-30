package com.example.Hotel.Review.and.Rating.System.service.implementation;

import com.example.Hotel.Review.and.Rating.System.authentication.LoginResponse;
import com.example.Hotel.Review.and.Rating.System.exception.ResourceNotFoundException;
import com.example.Hotel.Review.and.Rating.System.model.User;
import com.example.Hotel.Review.and.Rating.System.repository.UserRepo;
import com.example.Hotel.Review.and.Rating.System.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserServices {
    @Autowired
    private UserRepo userRepo;

    @Override
    public User saveUser(User emp) {
        return userRepo.save(emp);
    }

    @Override
    public LoginResponse authUser(User user) {
           String msg="";
           Optional<User> res=userRepo.findById(user.getUserId());
           if(!res.isPresent()){
               return new LoginResponse("User does not exit",false);
           }

           User consumer=res.get();
           if(!(consumer.getPassword().equals(user.getPassword()))){
               return new LoginResponse("Password not match",false);
           }
           else if(!(consumer.getTag().equals(user.getTag()))){
               return new LoginResponse("Tag not match",false);
           }
           else {
               return new LoginResponse("Login Successful", true);
           }
    }
}
