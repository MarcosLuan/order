package com.order.service;

import com.order.Enum.SituationEnum;
import com.order.exception.ResourceNotFoundException;
import com.order.model.Order;
import com.order.model.Stock;
import com.order.model.StockMovement;
import com.order.repository.OrderRepository;
import com.order.repository.StockMovementRepository;
import com.order.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StockMovementRepository stockMovementRepository;

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

        StockMovement newMovement = new StockMovement();
        newMovement.setCreationDate(LocalDate.now());
        newMovement.setItem(order.getItem());
        newMovement.setQuantity("removed " + order.getQuantity() + " piece(s) from stock");
        stockMovementRepository.save(newMovement);

        return newOrder;
    }

    public Order completeOrder(Order orderDetails) {
        Order completeOrder = orderRepository.findById(orderDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id: " + orderDetails.getId()));

        if (orderDetails.getSituation().equals(SituationEnum.CANCELED)) {
            new ResourceNotFoundException("The order is already canceled!");
        }

        completeOrder.setSituation(SituationEnum.COMPLETED);
        emailService.sendEmail(completeOrder);

        orderRepository.save(completeOrder);

        return completeOrder;
    }

    @Override
    public Order cancelOrder(Order orderDetails) {
        Order updateOrder = orderRepository.findById(orderDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id: " + orderDetails.getId()));

        updateOrder.setQuantity(0);
        updateOrder.setSituation(SituationEnum.CANCELED);
        emailService.sendEmail(updateOrder);

        orderRepository.save(updateOrder);

        stockService.stockChangeInput(orderDetails.getQuantity(), updateOrder.getItem());

        StockMovement newMovement = new StockMovement();
        newMovement.setCreationDate(LocalDate.now());
        newMovement.setItem(orderDetails.getItem());
        newMovement.setQuantity("added " + orderDetails.getQuantity() + " piece(s) in stock");
        stockMovementRepository.save(newMovement);

        return updateOrder;
    }

    public void deleteOrder(Order order) {
        Order deleteOrder = orderRepository.findById(order.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id: " + order.getId()));

        orderRepository.delete(deleteOrder);
        stockService.stockChangeInput(deleteOrder.getQuantity(), order.getItem());
    }

    public Order updateOrder(Order orderDetails) {
        Order updateOrder = orderRepository.findById(orderDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id: " + orderDetails.getId()));

        if (orderDetails.getSituation().equals(SituationEnum.CANCELED)) {
            new ResourceNotFoundException("The order is already canceled!");
        }

        if (orderDetails.getSituation().equals(SituationEnum.COMPLETED)) {
            new ResourceNotFoundException("The order is already completed!");
        }

        if (orderDetails.getQuantity() > updateOrder.getQuantity()) {
            stockService.stockChangeOutput(orderDetails.getQuantity() - updateOrder.getQuantity(), updateOrder.getItem());

            StockMovement newMovement = new StockMovement();
            newMovement.setCreationDate(LocalDate.now());
            newMovement.setItem(orderDetails.getItem());
            newMovement.setQuantity("removed " + (orderDetails.getQuantity() - updateOrder.getQuantity()) + " piece(s) from stock");
            stockMovementRepository.save(newMovement);
        } else if (orderDetails.getQuantity() < updateOrder.getQuantity()) {
            stockService.stockChangeInput(updateOrder.getQuantity() - orderDetails.getQuantity(), updateOrder.getItem());

            StockMovement newMovement = new StockMovement();
            newMovement.setCreationDate(LocalDate.now());
            newMovement.setItem(orderDetails.getItem());
            newMovement.setQuantity("added " + (updateOrder.getQuantity() - orderDetails.getQuantity()) + " piece(s) in stock");
            stockMovementRepository.save(newMovement);
        }

        updateOrder.setItem(orderDetails.getItem());
        updateOrder.setQuantity(orderDetails.getQuantity());
        updateOrder.setUserCreate(orderDetails.getUserCreate());
        orderRepository.save(updateOrder);

        return updateOrder;
    }
}
