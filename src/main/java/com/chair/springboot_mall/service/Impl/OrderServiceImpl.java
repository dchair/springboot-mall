package com.chair.springboot_mall.service.Impl;

import com.chair.springboot_mall.dao.OrderDao;
import com.chair.springboot_mall.dao.ProductDao;
import com.chair.springboot_mall.dto.BuyItem;
import com.chair.springboot_mall.dto.CreateOrderRequest;
import com.chair.springboot_mall.model.Order;
import com.chair.springboot_mall.model.OrderItem;
import com.chair.springboot_mall.model.Product;
import com.chair.springboot_mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public Order getOrderById(Integer orderId) {

        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    //只要同時需要注入兩個table就要記得使用Transactional 以免資料不對稱
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;

        //創一個List準備存放OrderItem的數據
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        //用foreach 把buyItem的資料放進去getBuyItemList
        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            //轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        Integer orderId = orderDao. createOrder(userId,totalAmount);



        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
