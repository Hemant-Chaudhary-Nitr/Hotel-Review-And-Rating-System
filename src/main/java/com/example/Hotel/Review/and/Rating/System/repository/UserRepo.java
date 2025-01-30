package com.example.Hotel.Review.and.Rating.System.repository;

import com.example.Hotel.Review.and.Rating.System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
}
