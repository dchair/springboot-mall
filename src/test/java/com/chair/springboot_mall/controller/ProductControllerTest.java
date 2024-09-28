package com.chair.springboot_mall.controller;

import com.chair.springboot_mall.constant.ProductCategory;
import com.chair.springboot_mall.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
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
    @Test
    public void getProduct_sucess() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/products/{productId},1");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName",equalTo("蘋果(澳洲)")))
                .andExpect(jsonPath("$.category",equalTo("FOOD")))
                .andExpect(jsonPath("$.imageUrl",notNullValue()))
                .andExpect(jsonPath("$.price",notNullValue()))
                .andExpect(jsonPath("$.stock",notNullValue()))
                .andExpect(jsonPath("$.description",notNullValue()))
                .andExpect(jsonPath("createdDate",notNullValue()))
                .andExpect(jsonPath("lastModifiedDate",notNullValue()));
    }

    @Test
    @Transactional
    public void getProduct_notFound() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products/{productId}",20000);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    //創建商品
    @Test
    @Transactional
    public void createProduct_sucess() throws Exception{
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("")

    }


    @Transactional
    @Test
    public void createProduct_illegalArgument() throws Exception{
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");
        String json = objectMapper.writeValueAsString(productRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));

    }



    @Test
    @Transactional
    public void createProduct_illegeArgument() throws Exception{
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder =MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    //更新商品
    @Transactional
    @Test
    public void updateProduct_success() throws Exception{
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");
        productRequest.setCategory(ProductCategory.FOOD);
        productRequest.setImageUrl("http://test.com");
        productRequest.setPrice(100);
        productRequest.setStock(2);

        String json =objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.productName",equalTo("test food product")))
                .andExpect(jsonPath("$.category",equalTo("FOOD")))
                .andExpect(jsonPath("$.imageUrl",equalTo("http://test.com")))
                .andExpect(jsonPath("$.price",equalTo(100)))
                .andExpect(jsonPath("$.stock",equalTo(2)))
                .andExpect(jsonPath("$.description",nullValue()))
                .andExpect(jsonPath("$.createdDate",notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate",notNullValue()));

    }


}