package jpabook.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

  @Id
  @GeneratedValue
  @Column(name = "DELIVERY_ID")
  private Long id;

  private String city;
  private String street;
  private String zipcode;
  private DeliveryStatus status;

  @OneToOne(mappedBy = "delivery")
  private Order order;
}
