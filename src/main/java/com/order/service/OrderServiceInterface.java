package com.order.service;

import com.order.model.Order;
import org.springframework.http.ResponseEntity;

public interface OrderServiceInterface {

    Order completeOrder(Order order);

    Order cancelOrder(Order order);

    Order newOrder(Order order);

    void deleteOrder(Order order);
}
