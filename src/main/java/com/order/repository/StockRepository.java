package com.order.repository;

import com.order.model.Item;
import com.order.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import javax.naming.Context;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByItem(Item item);

}
