package com.example.Hotel.Review.and.Rating.System;

import com.example.Hotel.Review.and.Rating.System.controller.BookingController;
import com.example.Hotel.Review.and.Rating.System.model.Review;
import com.example.Hotel.Review.and.Rating.System.service.BookingServices;
import com.example.Hotel.Review.and.Rating.System.service.ReviewServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Optional;

public class BookingControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private BookingServices bookingServices;

    @Mock
    private ReviewServices reviewServices;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @Test
    public void checkBookingExists_success() throws Exception {
        String userId = "1234";
        String hotelId = "abcd";
        String bookingId = "123456";

        boolean bookingExists = true;
        Mockito.when(bookingServices.getBookingDetails(userId, hotelId, bookingId)).thenReturn(bookingExists);

        Optional<Review> review = Optional.empty();
        Mockito.when(reviewServices.findByBookingId(bookingId)).thenReturn(review);


        mockMvc.perform(MockMvcRequestBuilders.get("/booking/exists")
                        .param("userId", userId)
                        .param("hotelId", hotelId)
                        .param("bookingId", bookingId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Boolean.toString(bookingExists)));
    }
}