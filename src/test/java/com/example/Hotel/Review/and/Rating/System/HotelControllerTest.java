package com.example.Hotel.Review.and.Rating.System;

import com.example.Hotel.Review.and.Rating.System.controller.HotelController;
import com.example.Hotel.Review.and.Rating.System.model.Hotel;
import com.example.Hotel.Review.and.Rating.System.service.HotelServices;
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
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class HotelControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private HotelServices hotelServices;

    @InjectMocks
    private HotelController hotelController;

    Hotel hotel = new Hotel("abcd","Taj","Shrey","9876543210",5,"Mumbai");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(hotelController).build();
    }

    @Test
    public void getHotel_success() throws Exception{
        Mockito.when(hotelServices.findHotel("abcd")).thenReturn(hotel);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/hotel/abcd")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelName").value("Taj"));
    }
}
