package com.order.controller;

import com.order.model.StockMovement;
import com.order.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock-movement")
public class StockMovementController {

    @Autowired
    private StockMovementRepository repository;

    @GetMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockMovement>> getStockMovement() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }
}
