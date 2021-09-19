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

        List<ShoeFilter> shoeFilterList = shoeRepository.findAll();
        List<Shoe> shoeList = shoeConverter.entityToDto(shoeFilterList);

        Stock stock = new Stock();
        stock.setShoes(shoeList);

        if (shoeList.size() == 0) {
            stock.setState(StockFilter.State.EMPTY);
            return stock;
        }

        // Loop to optimize with line below
        //BigInteger quantities = (BigInteger) shoeList.stream().map(x -> x.getQuantity().add(BigInteger.valueOf(0)));
        BigInteger quantities = new BigInteger(String.valueOf(0));
        for (Shoe shoe : shoeList) {
            quantities = quantities.add(shoe.getQuantity());
        }
        if (quantities.compareTo(Stock.stockCapacity) == 0 ) {
            stock.setState(StockFilter.State.FULL);
        } else {
            stock.setState(StockFilter.State.SOME);
        }

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
