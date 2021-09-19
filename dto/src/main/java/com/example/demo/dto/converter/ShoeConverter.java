package com.example.demo.dto.converter;

import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.out.Shoe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoeConverter {

    public Shoe entityToDto(ShoeFilter shoeFilter) {
        return new Shoe(shoeFilter.getQuantity(), shoeFilter.getSize(), shoeFilter.getColor());
    }

    public ShoeFilter dtoToEntity(Shoe shoe) {
        ShoeFilter shoeFilter = new ShoeFilter(shoe.getSize(), shoe.getColor(), shoe.getQuantity());
        return shoeFilter;
    }

    public List<Shoe> entityToDto(List<ShoeFilter> shoeFilterList) {
        return  shoeFilterList.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public List<ShoeFilter> dtoToEntity(List<Shoe> shoeList) {
        return  shoeList.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
