package jpabook.shop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
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
      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    emf.close();
  }
}