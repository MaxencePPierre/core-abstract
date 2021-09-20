package com.example.demo.controller;

import com.example.demo.dto.converter.ShoeConverter;
import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.in.StockFilter;
import com.example.demo.dto.out.Shoe;
import com.example.demo.dto.out.Shoes;
import com.example.demo.dto.out.Stock;
import com.example.jpa.ShoeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    ShoeRepository shoeRepository;

    @Autowired
    ShoeConverter shoeConverter;

    @GetMapping(value = "/stock")
    public Stock getStock(@RequestHeader Integer version) {
        LOGGER.info("Get stock");

        // Search for shoe list in repository
        List<ShoeFilter> shoeFilterList = shoeRepository.findAll();
        List<Shoe> shoeList = shoeConverter.entityToDto(shoeFilterList);

        // Return stock shoes list and current state
        Stock stock = new Stock();
        stock.setShoes(shoeList);
        stock.setState();

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
