package com.order.service;

import com.order.exception.ResourceNotFoundException;
import com.order.model.Item;
import com.order.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements ItemServiceInterface {

    @Autowired
    private ItemRepository repository;

    public Item itemChange(Item itemDetails) {
        Item updateItem = repository.findById(itemDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Item not exist with id: " + itemDetails.getId()));

        updateItem.setName(itemDetails.getName());
        repository.save(updateItem);

        return updateItem;
    }
}
