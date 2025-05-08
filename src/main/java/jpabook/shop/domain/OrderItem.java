package jpabook.shop.domain;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "ORDER_ITEM_ID")
  private Long id;


  // @Column(name = "ORDER_ID")
  // private Long orderId;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "ORDER_ID")
  private Order order;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "ITEM_ID")
  private Item item;

  private int orderPrice;
  private int count;


  public void updateOrder(Order order) {
    this.order = order;
    order.getOrderItem()
         .add(this);
  }
}
