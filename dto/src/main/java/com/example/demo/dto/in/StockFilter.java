package com.example.demo.dto.in;

import com.example.demo.dto.out.Shoes;
import lombok.Value;

import java.util.Date;

@Value
public class StockFilter {

    State state ;
    Shoes shoes;
    Date lastUpdate;


    public enum State{
        EMPTY,
        SOME,
        FULL,
    }

}
