package com.order.controller;

import com.order.model.Item;
import com.order.repository.ItemRepository;
import com.order.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @Autowired
    ItemService itemService;

    @GetMapping(value = "/search-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getItem() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/save-item", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Item postItem(@RequestBody Item item) {
        return repository.save(item);
    }

    @DeleteMapping(value = "/delete-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> deleteItem(@RequestBody Item item) {
        repository.delete(item);
        return ResponseEntity.ok(item);
    }

    @PutMapping(value ="/update-item", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> updateItem(@RequestBody Item itemDetails) {
        return ResponseEntity.ok(itemService.itemChange(itemDetails));
    }
}
