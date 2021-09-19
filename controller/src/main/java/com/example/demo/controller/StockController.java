package com.example.demo.controller;

import com.example.demo.dto.out.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {


    @GetMapping(value = "/stock")
    public Stock getStock() {
        Stock stock = new Stock();
        return stock;
    }

}
