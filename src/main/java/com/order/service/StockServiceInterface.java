package com.order.service;

import com.order.model.Item;
import com.order.model.Stock;

public interface StockServiceInterface {

    Stock stockChangeInput(Integer quantity, Item item);

    Stock stockChangeOutput(Integer quantity, Item item);
}
