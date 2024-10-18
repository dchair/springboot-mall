package com.chair.springboot_mall.service;

import com.chair.springboot_mall.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
