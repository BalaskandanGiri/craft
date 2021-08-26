package com.intuit.craft.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.craft.services.BusinessProfileService;
import com.intuit.craft.services.ProductService;
import com.intuit.craft.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BusinessProfileControllerTest {
    private static final String REST_URL = "/businessProfile";

    @InjectMocks
    BusinessProfileController businessProfileController;

    @Mock
    BusinessProfileService businessProfileService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(businessProfileController)
                .build();
    }

    @Test
    @DisplayName("Get all BusinessProfiles")
    void getAllBusinessProfiles() throws Exception{
        when(businessProfileService.getAllBusinessProfiles()).thenReturn(TestUtils.getBusinessProfiles());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(REST_URL)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is("1")))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Get one BusinessProfile")
    void getOneBusinessProfile() throws Exception{
        when(businessProfileService.getBusinessProfileById(Mockito.anyString())).thenReturn(TestUtils.getBusinessProfile());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(REST_URL+"/123")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")));
    }

    @Test
    @DisplayName("Delete BusinessProfile")
    void deleteBusinessProfile() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(REST_URL+"/123")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Update BusinessProfile")
    void updateBusinessProfile() throws Exception{
        when(businessProfileService.update(Mockito.any(), Mockito.any())).thenReturn(TestUtils.getBusinessProfile());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(REST_URL+"/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(TestUtils.getBusinessProfileData()))
                .characterEncoding("utf-8");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")));
    }

    @Test
    @DisplayName("Create BusinessProfile")
    void createBusinessProfile() throws Exception{
        when(businessProfileService.postBusinessProfile(Mockito.any())).thenReturn(TestUtils.getBusinessProfile());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(TestUtils.getBusinessProfileData()))
                .characterEncoding("utf-8");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("1")));
    }



}
