package com.example.demo.dto.in;

import com.example.demo.dto.out.Shoes;
import lombok.Value;

@Value
public class StockFilter {

    Shoes shoes;

    public enum State{
        EMPTY,
        SOME,
        FULL,
    }

}
