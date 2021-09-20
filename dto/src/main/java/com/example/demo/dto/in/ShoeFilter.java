package com.example.demo.dto.in;

import com.example.demo.dto.out.Shoe;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Optional;

@Entity
@Table( name = "SHOE")
public class ShoeFilter {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
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

  public Long getId() {
    return id;
  }
}
