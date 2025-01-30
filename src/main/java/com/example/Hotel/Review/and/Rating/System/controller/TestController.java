package com.example.Hotel.Review.and.Rating.System.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class TestController {
   Logger logger= LoggerFactory.getLogger(TestController.class);
    @GetMapping()
    String test(String args){
        logger.info("application start running");
        System.out.println("printing");
        return "hello";
    }
}
