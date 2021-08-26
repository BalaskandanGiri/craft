package com.intuit.craft.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ProductControllerTest {
    private static final String REST_URL = "/product";

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(productController)
                .build();
    }

    @Test
    @DisplayName("Get all Products")
    void getAllProducts() throws Exception{
        when(productService.getAllProducts()).thenReturn(TestUtils.getProducts());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(REST_URL)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[1].name", is("product2")))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Get one Product")
    void getOneProduct() throws Exception{
        when(productService.getProductById(Mockito.anyString())).thenReturn(TestUtils.getProduct());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(REST_URL+"/123")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("123")));
    }

    @Test
    @DisplayName("Delete Product")
    void deleteProduct() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(REST_URL+"/123")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Update Product")
    void updateProduct() throws Exception{
        when(productService.update(Mockito.any(), Mockito.any())).thenReturn(TestUtils.getProduct());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(REST_URL+"/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(TestUtils.getProductData()))
                .characterEncoding("utf-8");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("123")));
    }

    @Test
    @DisplayName("Create Product")
    void createProduct() throws Exception{
        when(productService.postProduct(Mockito.any())).thenReturn(TestUtils.getProduct());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(TestUtils.getProductData()))
                .characterEncoding("utf-8");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("123")));
    }



}
