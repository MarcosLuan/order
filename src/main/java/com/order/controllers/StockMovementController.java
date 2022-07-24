package com.order.controllers;

import com.order.model.StockMovement;
import com.order.model.UserCreate;
import com.order.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/stock-movement")
public class StockMovementController {

    @Autowired
    private StockMovementRepository repository;

    @RequestMapping(value = "/stock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockMovement>> getStockMovement() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }
}
