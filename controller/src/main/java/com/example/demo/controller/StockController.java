package com.example.demo.controller;

import com.example.demo.dto.converter.ShoeConverter;
import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.in.StockFilter;
import com.example.demo.dto.out.Shoe;
import com.example.demo.dto.out.Stock;
import com.example.demo.errors.ErrBadRequest;
import com.example.demo.errors.ErrConflict;
import com.example.demo.facade.StockFacade;
import com.example.demo.repository.ShoeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@ControllerAdvice
@Api("Stock API.")
@RequestMapping(path = "/stock")
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    ShoeRepository shoeRepository;

    @Autowired
    ShoeConverter shoeConverter;

    @Autowired
    StockFacade stockFacade;

    @ApiOperation(value = "Return the state of the stock, with the list of shoes.")
    @GetMapping()
    public Stock getStock() {
        LOGGER.info("Get stock");

        // Search for shoe list in repository
        List<ShoeFilter> shoeFilterList = shoeRepository.findAll();

        // Return stock shoes list and current state
        return shoeConverter.toStock(shoeFilterList);
    }

    @ResponseBody
    @PostMapping(value="/shoe")
    @ApiOperation(value = "Add a shoe to the stock.")
    public ResponseEntity<Object> updateShoeStock(@RequestBody Shoe shoe) {
        LOGGER.info("Stock shoe updated");

        // Load stock
        List<ShoeFilter> shoeFilterList = shoeRepository.findAll();
        Stock stock = shoeConverter.toStock(shoeFilterList);

        // Verify stock status
        int requestQuantity = shoe.getQuantity().intValue();
        if (stock.getState() == StockFilter.State.FULL && requestQuantity > 0){
            LOGGER.debug("Conflict: Stock is full");
            throw new ErrConflict("Stock is full. Maximum capacity is: " + Stock.maxStockCapacity.toString());
        }

        // Check the quantity provided will not exceed the stock capacity
        BigInteger quantities = stockFacade.getTotalQuantity(stock);
        int expected = quantities.add(shoe.getQuantity()).intValue();
        if (expected > Stock.maxStockCapacity) {
            throw new ErrConflict("Exceeding the maximum stock capacity. Maximum capacity is: " + Stock.maxStockCapacity);
        }

        // Write in repo
        ShoeFilter currentShoe = shoeRepository.findByColorAndSize(shoe.getColor(), shoe.getSize());
        if (currentShoe == null) {
            if (requestQuantity < 0) {
                throw new ErrBadRequest("Invalid body. quantity: " + shoe.getQuantity().toString());
            }
            LOGGER.debug("Shoe does not exist in stock");
            shoeRepository.save(shoeConverter.dtoToEntity(shoe));
            return ResponseEntity.noContent().build();
        }

        LOGGER.debug("Shoe is in stock");
        BigInteger newQuantity = currentShoe.getQuantity().add(shoe.getQuantity());
        if (newQuantity.intValue() < 0) {
            throw new ErrBadRequest("Invalid body. quantity: " + shoe.getQuantity().toString());
        }
        shoeRepository.updateShoeQuantity(currentShoe.getId(), newQuantity);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Add a list of shoes to the stock.")
    @PostMapping(value="/shoes")
    public ResponseEntity<Object> updateShoesStock(@RequestBody List<Shoe> shoes) {
        LOGGER.info("Stock shoes updated");

        if (shoes.size() == 0) {
            return ResponseEntity.noContent().build();
        }

        // Load stock
        List<ShoeFilter> shoeFilterList = shoeRepository.findAll();
        Stock stock = shoeConverter.toStock(shoeFilterList);

        // Check stock capacity will not exceed
        Stock stockRequest = new Stock(shoes);
        BigInteger quantityRequest = stockFacade.getTotalQuantity(stockRequest);
        BigInteger quantity        = stockFacade.getTotalQuantity(stock);
        if (quantity.add(quantityRequest).intValue() > Stock.maxStockCapacity) {
            throw new ErrBadRequest("Invalid body. total quantity provided: " + quantityRequest);
        }

        // Before writing in database we check all shoe quantities are valid for each
        // Nice to have: transactional query to rollback/commit
        for (Shoe shoe : shoes) {
            ShoeFilter currentShoe = shoeRepository.findByColorAndSize(shoe.getColor(), shoe.getSize());
            if (currentShoe == null) {
                if (shoe.getQuantity().intValue() <= 0) {
                    throw new ErrBadRequest("Invalid body. quantity: " + shoe.getQuantity().toString());
                }
            } else {
                // We cannot remove more shoes than exist in stock
                BigInteger newQuantity = currentShoe.getQuantity().add(shoe.getQuantity());
                if (newQuantity.intValue() < 0) {
                    throw new ErrBadRequest("Invalid body. Invalid quantity: " + shoe.getQuantity().toString() + " for " + shoe.toString());
                }
            }
        }

        // Write in repo
        for (Shoe shoe : shoes) {
            ShoeFilter currentShoe = shoeRepository.findByColorAndSize(shoe.getColor(), shoe.getSize());
            if (currentShoe == null) {
                shoeRepository.save(shoeConverter.dtoToEntity(shoe));
            } else {
                // We cannot remove more shoes than exist in stock
                BigInteger newQuantity = currentShoe.getQuantity().add(shoe.getQuantity());
                shoeRepository.updateShoeQuantity(currentShoe.getId(), newQuantity);
            }
        }

        return ResponseEntity.noContent().build();
    }
}
