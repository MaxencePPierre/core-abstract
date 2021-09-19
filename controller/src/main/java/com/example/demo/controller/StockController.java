package com.example.demo.controller;

import com.example.demo.dto.out.Shoe;
import com.example.demo.dto.out.Shoes;
import com.example.demo.dto.out.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @GetMapping(value = "/stock")
    public Stock getStock(@RequestHeader Integer version) {
        LOGGER.info("Get stock");
        Stock stock = new Stock();
        return stock;
    }

    @PostMapping(value="/stock")
    public ResponseEntity<Void> updateShoeStock(@RequestBody Shoe shoe, @RequestHeader Integer version) {
        LOGGER.info("Stock shoe updated");
        LOGGER.info(shoe.toString());
        return ResponseEntity.noContent().build();
    }
    public ResponseEntity<Void> updateShoesStock(@RequestBody Shoes shoes, @RequestHeader Integer version) {
        LOGGER.info("Stock shoes updated");
        return ResponseEntity.noContent().build();
    }

}
