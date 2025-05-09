package jpabook.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
public class AddressEntity {

  @Embedded
  private Address address;
  @Id
  @GeneratedValue
  @Column(name = "ADDRESS_HISTORY_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "MEMBER_ID", insertable = false, updatable = false)
  private Member member;

  public AddressEntity() {
  }

  public AddressEntity(Address address) {
    this.address = address;
  }
}

