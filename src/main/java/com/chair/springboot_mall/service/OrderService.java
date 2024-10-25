package com.chair.springboot_mall.service;

import com.chair.springboot_mall.dto.CreateOrderRequest;
import com.chair.springboot_mall.dto.OrderQueryParams;
import com.chair.springboot_mall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
