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
public class CategoryItem {

  @Id
  @GeneratedValue
  @Column(name = "CATEGORY_ITEM_ID")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "ITEM_ID")
  private Item item;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "CATEGORY_ID")
  private Category category;

  // convenient methods
  public void updateItem(Item item) {
    this.item = item;
    item.getCategoryItemList()
        .add(this);
  }

  public void updateCategory(Category category) {
    this.category = category;
    category.getItemCategoryList()
            .add(this);
  }
}
