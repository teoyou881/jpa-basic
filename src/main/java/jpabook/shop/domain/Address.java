package jpabook.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

  @Column(length = 50)
  private String city;
  @Column(length = 50)
  private String street;
  @Column(length = 10)
  private String zipcode;

  public Address() {
  }

  public Address(String city, String street, String zipcode) {
    this.city = city;
    this.street = street;
    this.zipcode = zipcode;
  }

  @Override
  public final boolean equals(Object o) {
    if (!(o instanceof Address address)) {
      return false;
    }

    return Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet())
        && Objects.equals(getZipcode(), address.getZipcode());
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(getCity());
    result = 31 * result + Objects.hashCode(getStreet());
    result = 31 * result + Objects.hashCode(getZipcode());
    return result;
  }
}
