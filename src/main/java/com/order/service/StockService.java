package com.order.service;

import com.order.exception.ResourceNotFoundException;
import com.order.model.Item;
import com.order.model.Stock;
import com.order.repository.ItemRepository;
import com.order.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService implements StockServiceInterface {

    @Autowired
    private StockRepository repository;

    public Stock stockChangeInput(Integer quantity, Item item) {
        Stock updateStock = repository.findByItem(item).stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Stock not exist"));

        updateStock.setInputAmount(updateStock.getInputAmount() + quantity);
        updateStock.setCurrentAmount(updateStock.getInputAmount() - updateStock.getQuantityMoved());
        repository.save(updateStock);

        return updateStock;
    }

    public Stock stockChangeOutput(Integer quantity, Item item) {
        Stock updateStock = repository.findByItem(item).stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Stock not exist"));

        updateStock.setQuantityMoved(updateStock.getQuantityMoved() + quantity);
        updateStock.setCurrentAmount(updateStock.getInputAmount() - updateStock.getQuantityMoved());
        repository.save(updateStock);

        return updateStock;
    }
}
