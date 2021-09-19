package com.example.demo.dto.in;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Optional;

@Entity
public class ShoeFilter {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;
  BigInteger   quantity;
  BigInteger   size;
  Color        color;

  public enum Color{
    BLACK,
    BLUE,
  }

  public ShoeFilter() {
  }

  public ShoeFilter(BigInteger size, Color color, BigInteger quantity) {
    this.size = size;
    this.color = color;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigInteger getQuantity() {
    return quantity;
  }

  public void setQuantity(BigInteger quantity) {
    this.quantity = quantity;
  }

  public BigInteger getSize() {
    return size;
  }

  public void setSize(BigInteger size) {
    this.size = size;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "ShoeFilter{" +
            "id=" + id +
            ", size=" + size +
            ", color=" + color +
            ", quantity=" + quantity +
            '}';
  }
}
