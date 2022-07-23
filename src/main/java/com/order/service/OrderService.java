package com.order.service;

import com.order.Enum.SituationEnum;
import com.order.exception.ResourceNotFoundException;
import com.order.model.Order;
import com.order.model.Stock;
import com.order.repository.ItemRepository;
import com.order.repository.OrderRepository;
import com.order.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService implements OrderServiceInterface {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private EmailService emailService;

    public Order orderChange(Order orderDetails) {
        Order updateOrder = orderRepository.findById(orderDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id: " + orderDetails.getId()));

        updateOrder.setItem(orderDetails.getItem());
        updateOrder.setQuantity(orderDetails.getQuantity());
        updateOrder.setUserCreate(orderDetails.getUserCreate());

        if (orderDetails.getSituation().equals(SituationEnum.COMPLETED)) {
            updateOrder.setSituation(SituationEnum.COMPLETED);
            emailService.sendEmail(orderDetails);
        }

        orderRepository.save(updateOrder);

        if (orderDetails.getQuantity() > updateOrder.getQuantity()) {
            stockService.stockChangeOutput(orderDetails.getQuantity() - updateOrder.getQuantity(), updateOrder.getItem());
        } else if (orderDetails.getQuantity() < updateOrder.getQuantity()) {
            stockService.stockChangeInput(updateOrder.getQuantity() - orderDetails.getQuantity(), updateOrder.getItem());
        }

        return updateOrder;
    }

    public Order newOrder(Order order) {
        List<Stock> stock = stockRepository.findByItem(order.getItem());

        if (stock.stream().findFirst().get().getCurrentAmount() < order.getQuantity()) {
            throw new ResourceNotFoundException("Insufficient quantity in stock!");
        }

        Order newOrder = new Order();
        newOrder.setUserCreate(order.getUserCreate());
        newOrder.setItem(order.getItem());
        newOrder.setQuantity(order.getQuantity());
        newOrder.setSituation(SituationEnum.IN_PROGRESS);
        newOrder.setUserCreate(order.getUserCreate());
        newOrder.setCreationDate(LocalDate.now());

        orderRepository.save(newOrder);
        stockService.stockChangeOutput(order.getQuantity(), order.getItem());

        return newOrder;
    }

    public void deleteOrder(Order order) {
        Order deleteOrder = orderRepository.findById(order.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id: " + order.getId()));

        orderRepository.delete(deleteOrder);
        stockService.stockChangeInput(deleteOrder.getQuantity(), order.getItem());
    }
}
