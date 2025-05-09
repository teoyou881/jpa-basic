package jpabook.shop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jpabook.shop.domain.Address;
import jpabook.shop.domain.AddressEntity;
import jpabook.shop.domain.Member;
import jpabook.shop.domain.Option;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShopApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(ShopApplication.class, args);
    EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Member member = new Member();
      member.setHomeAddress(new Address("Seoul", "Jungreong", "02815"));

      member.getFavoriteFoods()
            .add("chicken");
      member.getFavoriteFoods()
            .add("beef");
      member.getFavoriteFoods()
            .add("pork");

      member.getOptions()
            .add(new Option(100, "white", "L"));
      member.getOptions()
            .add(new Option(200, "black", "M"));
      member.getOptions()
            .add(new Option(300, "red", "XL"));
      member.getOptions()
            .add(new Option(400, "orange", "XXL"));

      member.getAddressesHistory()
            .add(new AddressEntity(new Address("test1", "Jungreong", "02815")));
      member.getAddressesHistory()
            .add(new AddressEntity(new Address("test2", "Jungreong", "02815")));

      em.persist(member);
      em.flush();
      em.clear();

      System.out.println("===================");

      Member findMember = em.find(Member.class, member.getId());

      // findMember.getFavoriteFoods()
      //           .remove("beef");
      // findMember.getFavoriteFoods()
      //           .add("zzz");

      // findMember.getOptions()
      //           .add(new Option(500, "green", "S"));

      findMember.getAddressesHistory()
                .add(new AddressEntity(new Address("test3", "Jungreong", "02815")));

      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    emf.close();
  }
}