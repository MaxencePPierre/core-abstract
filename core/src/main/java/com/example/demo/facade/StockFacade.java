package com.example.demo.facade;

import com.example.demo.dto.out.Shoe;
import com.example.demo.dto.out.Stock;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class StockFacade {

    public BigInteger getTotalQuantity(Stock stock) {
        BigInteger totalQuantity = new BigInteger(String.valueOf(0));
        for (Shoe shoe : stock.getShoes()) {
            totalQuantity = totalQuantity.add(shoe.getQuantity());
        }

        return totalQuantity;
    }
}
