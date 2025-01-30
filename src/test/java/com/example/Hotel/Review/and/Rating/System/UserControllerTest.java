package com.example.Hotel.Review.and.Rating.System;

import com.example.Hotel.Review.and.Rating.System.authentication.LoginResponse;
import com.example.Hotel.Review.and.Rating.System.controller.UserController;
import com.example.Hotel.Review.and.Rating.System.model.User;
import com.example.Hotel.Review.and.Rating.System.service.UserServices;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private UserServices userServices;

    @InjectMocks
    private UserController userController;

    User user =new User("1234","abcd","user");

    LoginResponse loginResponse = new LoginResponse("success",true);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void authUser_success() throws Exception{
        Mockito.when(userServices.authUser(user)).thenReturn(loginResponse);
        String content = objectWriter.writeValueAsString(user);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true));
    }
}
