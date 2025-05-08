package jpabook.shop.domain;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "CATEGORY_ID")
  private Long id;
  private String name;

  // 다대일: 여러 자식 카테고리가 하나의 부모를 가질 수 있음
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "PARENT_ID")
  private Category parent;

  // 일대다: 하나의 부모 카테고리가 여러 자식 카테고리를 가질 수 있음
  @OneToMany(mappedBy = "parent")
  private List<Category> children = new ArrayList<>();
  @OneToMany(mappedBy = "category")
  private List<CategoryItem> itemCategoryList;

  // convenience methods
  public void updateChildCategory(Category category) {
    this.children.add(category);
    category.setParent(this);
  }

}
