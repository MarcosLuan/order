package com.order.controllers;

import com.order.model.Order;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/order-manager")
public class OrderManagerController {

    @Autowired
    private OrderRepository repository;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/search-order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrder() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/create-order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> newOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.newOrder(order));
    }

    @RequestMapping(value = "/delete-order", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteOrder(@RequestBody Order order) {
        orderService.deleteOrder(order);
    }

    @RequestMapping(value ="/complete-order", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> completeOrder(@RequestBody Order orderDetails) {
        return ResponseEntity.ok(orderService.completeOrder(orderDetails));
    }

    @RequestMapping(value ="/cancel-order", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> cancelOrder(@RequestBody Order orderDetails) {
        return ResponseEntity.ok(orderService.cancelOrder(orderDetails));
    }
}

