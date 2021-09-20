package com.example.demo.dto.out;

import com.example.demo.dto.in.StockFilter;

import java.math.BigInteger;
import java.util.List;

public class Stock {
    public static Integer maxStockCapacity = 30;
    public static Integer minStockCapacity = 0;


    StockFilter.State state;
    List<Shoe> shoes;

    public Stock(StockFilter.State state, List<Shoe> shoes) {
        this.state = state;
        this.shoes = shoes;
    }

    public Stock() {
    }

    public StockFilter.State getState() {
        return state;
    }

    public void setState(StockFilter.State state) {
        this.state = state;
    }

    public void setState() {
        if (this.shoes.size() == 0) {
            this.state = StockFilter.State.EMPTY;
            return;
        }

        BigInteger quantities = new BigInteger(String.valueOf(0));
        for (Shoe shoe : this.shoes) {
            quantities = quantities.add(shoe.getQuantity());
        }

        int quantity = quantities.intValue();
        if (quantity >= Stock.maxStockCapacity) {
            this.state = StockFilter.State.FULL;
        } else if (quantity == Stock.minStockCapacity) {
            this.state = StockFilter.State.EMPTY;
        } else {
            this.state = StockFilter.State.SOME;
        }

        return;
    }

    public List<Shoe> getShoes() {
        return shoes;
    }

    public void setShoes(List<Shoe> shoes) {
        this.shoes = shoes;
    }

}

