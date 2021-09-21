package com.example.demo.dto.out;

import com.example.demo.dto.out.Shoes.ShoesBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;


@JsonDeserialize(builder = ShoesBuilder.class)
public class Shoes {

  List<Shoe> shoes;

  public Shoes() {

  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class ShoesBuilder {

  }


  public List<Shoe> getShoes() {
    return shoes;
  }

  public void setShoes(List<Shoe> shoes) {
    this.shoes = shoes;
  }

  public Shoes(List<Shoe> shoes) {
    this.shoes = shoes;
  }
}
