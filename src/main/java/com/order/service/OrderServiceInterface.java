package com.order.service;

import com.order.model.Order;
import org.springframework.http.ResponseEntity;

public interface OrderServiceInterface {

    Order newOrder(Order order);

    Order completeOrder(Order order);

    Order cancelOrder(Order order);

    void deleteOrder(Order order);

    Order updateOrder(Order order);
}
