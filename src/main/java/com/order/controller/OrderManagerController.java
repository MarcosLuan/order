package com.order.controller;

import com.order.model.Order;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-manager")
public class OrderManagerController {

    @Autowired
    private OrderRepository repository;

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/search-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrder() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/create-order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> newOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.newOrder(order));
    }

    @PutMapping(value ="/complete-order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> completeOrder(@RequestBody Order orderDetails) {
        return ResponseEntity.ok(orderService.completeOrder(orderDetails));
    }

    @PutMapping(value ="/cancel-order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> cancelOrder(@RequestBody Order orderDetails) {
        return ResponseEntity.ok(orderService.cancelOrder(orderDetails));
    }

    @DeleteMapping(value = "/delete-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> deleteOrder(@RequestBody Order order) {
        orderService.deleteOrder(order);
        return ResponseEntity.ok(order);
    }

    @PutMapping(value ="/update-order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateOrder(@RequestBody Order orderDetails) {
        return ResponseEntity.ok(orderService.updateOrder(orderDetails));
    }
}

