package jpabook.shop.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id;
  private String name;

  @Embedded
  private Address homeAddress;

  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @jakarta.persistence.JoinColumn(name = "MEMBER_ID"))
  @Column(name = "FOOD_NAME")
  private Set<String> favoriteFoods = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "OPTION", joinColumns = @JoinColumn(name = "MEMBER_ID"))
  private List<Option> options = new ArrayList<>();

  // 일대다 매핑
  // 틀별한 경우이다. 보통은 다대일로 매핑해야 한다.
  // 일대다로 매핑하고 cascade, orphanremoval 옵션을 사용한 이유는 생명주기를 관리하기 위해서.
  @OneToMany(cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "MEMBER_ID")
  private List<AddressEntity> addressesHistory = new ArrayList<>();

  // convenient methods
  public void addAddress(AddressEntity address) {
    addressesHistory.add(address);
    address.setMember(this);
  }

}
