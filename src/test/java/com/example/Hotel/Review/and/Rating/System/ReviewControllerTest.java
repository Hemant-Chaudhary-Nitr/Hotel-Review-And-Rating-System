package com.example.Hotel.Review.and.Rating.System;

import com.example.Hotel.Review.and.Rating.System.controller.ReviewController;
import com.example.Hotel.Review.and.Rating.System.model.Review;
import com.example.Hotel.Review.and.Rating.System.service.CommentServices;
import com.example.Hotel.Review.and.Rating.System.service.HotelServices;
import com.example.Hotel.Review.and.Rating.System.service.ReviewServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ReviewControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private ReviewServices reviewServices;

    @Mock
    private CommentServices commentServices;

    @Mock
    private HotelServices hotelServices;

    @InjectMocks
    private ReviewController reviewController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }


    @Test
    public void getAllReviewByUserId_success() throws Exception {
        List<Review> reviews = Arrays.asList(new Review(), new Review());
        Mockito.when(reviewServices.getAllReviewByUserId("1234")).thenReturn(reviews);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/review/userReview/1234")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(reviews.size()));
    }

    @Test
    public void getAllReviewByHotelId_success() throws Exception {
        List<Review> reviews = Arrays.asList(new Review(), new Review(), new Review());
        Mockito.when(reviewServices.getAllReviewByHotelId("abcd")).thenReturn(reviews);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/review/hotelReview/abcd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(reviews.size()));
    }

    @Test
    public void saveReview_success() throws Exception {
        double averageRating = 4.5;
        Date timestamp = new Date();
        Review review = new Review(1, "1234", "abcd", 4.5, "good", "123456", timestamp);

        Mockito.when(reviewServices.saveReviewDetails(review)).thenReturn(review);
        Mockito.when(hotelServices.calculateAverageRatingAndUpdateHotel(review.getHotelId())).thenReturn(averageRating);

        String content = objectWriter.writeValueAsString(review);

        // Perform the mock HTTP POST request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelId").value("abcd"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(4.5));
    }

    @Test
    public void deleteReview_success() throws Exception{
        Date timestamp = new Date();
        Review review = new Review(1, "1234", "abcd", 4.5, "good", "123456", timestamp);

        Mockito.when(reviewServices.deleteReviewDetails(1)).thenReturn(review);
        doNothing().when(commentServices).deleteCommentDetails(1);

        double averageRating = 4.5;
        Mockito.when(hotelServices.calculateAverageRatingAndUpdateHotel(review.getHotelId())).thenReturn(averageRating);


        mockMvc.perform(MockMvcRequestBuilders.delete("/review/deleteReview/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.reviewId").value(1));
    }
}