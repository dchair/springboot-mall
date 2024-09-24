package com.chair.springboot_mall.controller;

import com.chair.springboot_mall.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper=new ObjectMapper();

    //查詢商品

    public void getProduct_sucess() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/products/{productId},1");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName",equalTo("蘋果(澳洲)")))
                .andExpect(jsonPath("$.categoryId",equalTo("FOOD")))
                .andExpect(jsonPath("$.imageUrl",notNullValue()))
                .andExpect(jsonPath("$.price",notNullValue()))
                .andExpect(jsonPath("$.stock",notNullValue()))
                .andExpect(jsonPath("$.description",notNullValue()))
                .andExpect(jsonPath("createdDate",notNullValue()))
                .andExpect(jsonPath("lastModifiedDate",notNullValue()));
    }

    @Transactional
    @Test
    public void createProduct_illegalArgument() throws Exception{
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProduct
    }



}