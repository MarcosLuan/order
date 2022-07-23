package com.order.service;

import com.order.model.Order;
import org.springframework.http.ResponseEntity;

public interface OrderServiceInterface {

    Order orderChange(Order id);

    Order newOrder(Order order);

    void deleteOrder(Order order);
}
