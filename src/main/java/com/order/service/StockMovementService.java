package com.order.service;

import com.order.exception.ResourceNotFoundException;
import com.order.model.StockMovement;
import com.order.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockMovementService implements StockMovementServiceInterface {

    @Autowired
    private StockMovementRepository repository;

    public StockMovement stockMovementChange(StockMovement stockMovementDetails) {
        StockMovement updateStockMovement = repository.findById(stockMovementDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Stock Movement not exist with id: " + stockMovementDetails.getId()));

        updateStockMovement.setItem(stockMovementDetails.getItem());
        updateStockMovement.setQuantity(stockMovementDetails.getQuantity());
        updateStockMovement.setCreationDate(stockMovementDetails.getCreationDate());

        repository.save(updateStockMovement);

        return updateStockMovement;
    }
}
