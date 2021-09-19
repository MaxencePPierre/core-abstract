package com.example.demo.dto.out;

import com.example.demo.dto.in.StockFilter;

import java.math.BigInteger;
import java.util.List;

public class Stock {
    public static BigInteger stockCapacity = new BigInteger(String.valueOf(30));


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

    public List<Shoe> getShoes() {
        return shoes;
    }

    public void setShoes(List<Shoe> shoes) {
        this.shoes = shoes;
    }



}

