package com.chair.springboot_mall.controller;

import com.chair.springboot_mall.dto.CreateOrderRequest;
import com.chair.springboot_mall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest) {
        Integer orderId =orderService.createOrder(userId,createOrderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

}
