package jpabook.shop.domain;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ORDERS")
public class Order extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "ORDER_ID")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "MEMBER_ID")
  private Member member;

  private LocalDateTime orderDate;
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @OneToMany(mappedBy = "order", cascade = ALL)
  private List<OrderItem> orderItem = new ArrayList<>();

  // order를 통해서 delivery를 다 관리하겠다. ==> 라이프 사이클을 관리하겠다.
  @OneToOne(fetch = LAZY, cascade = ALL)
  @JoinColumn(name = "DELIVERY_ID")
  private Delivery delivery;

  // Convenient ways
  public void updateMember(Member member) {
    this.member = member;
    member.getOrders()
          .add(this);
  }

}
