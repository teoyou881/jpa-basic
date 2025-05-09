package jpabook.shop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Option {

  private Double price;
  private String color;
  private String size;

  public Option(double price, String color, String size) {
    this.price = price;
    this.color = color;
    this.size = size;
  }

  public Option() {

  }
}
