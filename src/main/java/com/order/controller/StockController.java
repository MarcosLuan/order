package com.order.controller;

import com.order.model.Item;
import com.order.model.Stock;
import com.order.repository.StockRepository;
import com.order.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository repository;

    @Autowired
    StockService stockService;

    @GetMapping(value = "/search-stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Stock>> getItem() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value ="/update-stock/{quantity}/{item}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> updateStock(@PathVariable Integer quantity, @PathVariable Item item) {
        try {
            List<Stock> stockItem = repository.findByItem(item);

            if (stockItem.size() == 0) {
                Stock stock = new Stock();
                stock.setInputAmount(0);
                stock.setQuantityMoved(0);
                stock.setCurrentAmount(0);
                stock.setItem(item);
                repository.save(stock);
            }
            return ResponseEntity.ok(stockService.stockChangeInput(quantity, item));
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }

    }
}
