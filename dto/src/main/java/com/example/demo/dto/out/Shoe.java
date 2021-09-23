package com.example.demo.dto.out;

import com.example.demo.dto.in.ShoeFilter.Color;
import com.example.demo.dto.out.Shoe.ShoeBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.math.BigInteger;
import lombok.Builder;
import lombok.Value;

@Builder
@JsonDeserialize(builder = ShoeBuilder.class)
public class Shoe {
  public static Integer maxSize = 50;
  public static Integer minSize = 19;

  BigInteger quantity;
  BigInteger size;
  Color      color;

  @JsonPOJOBuilder(withPrefix = "")
  public static class ShoeBuilder {

  }

  public Shoe(BigInteger quantity, BigInteger size, Color color) {
    this.quantity = quantity;
    this.size = size;
    this.color = color;
  }

  public void setQuantity(BigInteger quantity) {
    this.quantity = quantity;
  }

  public BigInteger getQuantity() {
    return quantity;
  }

  public BigInteger getSize() {
    return size;
  }

  public Color getColor() {
    return color;
  }

  @Override
  public String toString() {
    return "Shoe{" +
            "size=" + size +
            ", color=" + color +
            '}';
  }
}
