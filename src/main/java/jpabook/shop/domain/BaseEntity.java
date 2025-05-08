package jpabook.shop.domain;


import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

  private String createdBy;
  private String lastModifiedBy;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
}
