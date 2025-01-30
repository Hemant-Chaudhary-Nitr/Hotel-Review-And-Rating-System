package com.example.Hotel.Review.and.Rating.System;

import com.example.Hotel.Review.and.Rating.System.controller.CommentController;
import com.example.Hotel.Review.and.Rating.System.model.Comment;
import com.example.Hotel.Review.and.Rating.System.service.CommentServices;
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
import java.util.Date;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private CommentServices commentServices;

    @InjectMocks
    private CommentController commentController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    Date timestamp = new Date();
    Comment comment = new Comment(1,1,"1234","test comment",timestamp);

    @Test
    public void getComment_success() throws Exception{
        Mockito.when(commentServices.getCommentDetails(1)).thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/comment/getComment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reply").value("test comment"));
    }

    @Test
    public void saveComment_success() throws Exception{
        Mockito.when(commentServices.saveCommentDetails(comment)).thenReturn(comment);
        String content = objectWriter.writeValueAsString(comment);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.userId",is("1234")))
                .andExpect(jsonPath("$.reviewId",is(1)));
    }

    @Test
    public void deleteComment_success() throws Exception{
        Integer reviewId = 1;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/comment/deleteComment/{id}", reviewId);

        // Perform the mock HTTP DELETE request and assert the response
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }
}
