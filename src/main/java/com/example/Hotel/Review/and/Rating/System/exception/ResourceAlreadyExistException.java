package com.example.Hotel.Review.and.Rating.System.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceAlreadyExistException extends RuntimeException{
    public ResourceAlreadyExistException(String msg){
        super(msg);
    }
}
