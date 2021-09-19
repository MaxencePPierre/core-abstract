package com.example.demo.dto.out;

import com.example.demo.dto.in.StockFilter;

public class Stock {

    private StockFilter.State state;
    private Shoes shoes;

    public Stock(StockFilter.State state, Shoes shoes) {
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

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }
}

