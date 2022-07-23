package com.order.controllers;

import com.order.model.StockMovement;
import com.order.repository.StockMovementRepository;
import com.order.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/stock-movement")
public class StockMovementController {

    @Autowired
    private StockMovementRepository repository;

    @Autowired
    StockMovementService stockMovementService;

    @RequestMapping(value = "/search-stock-movement", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockMovement>> getStockMovement() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/save-stock-movement", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public StockMovement postStockMovement(@RequestBody StockMovement stock) {
        return repository.save(stock);
    }

    @RequestMapping(value = "/delete-stock-movement", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStockMovementr(@RequestBody StockMovement stock) {
        repository.delete(stock);
    }

    @RequestMapping(value ="/update-stock-movement", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockMovement> updateStockMovement(@RequestBody StockMovement stockMovementDetails) {
        return ResponseEntity.ok(stockMovementService.stockMovementChange(stockMovementDetails));
    }
}

