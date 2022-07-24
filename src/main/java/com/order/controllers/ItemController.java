package com.order.controllers;

import com.order.model.Item;
import com.order.repository.ItemRepository;
import com.order.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/search-item", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getItem() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/save-item", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Item postItem(@RequestBody Item item) {
        return repository.save(item);
    }

    @RequestMapping(value = "/delete-item", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteItem(@RequestBody Item item) {
        repository.delete(item);
    }

    @RequestMapping(value ="/update-item", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> updateItem(@RequestBody Item itemDetails) {
        return ResponseEntity.ok(itemService.itemChange(itemDetails));
    }
}
