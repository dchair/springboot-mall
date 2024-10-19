package com.chair.springboot_mall.service;

import com.chair.springboot_mall.dto.CreateOrderRequest;
import com.chair.springboot_mall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
