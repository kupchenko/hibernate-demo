package me.kupchenko.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 */
public class JpaDemo {
    private EntityManagerFactory entityManagerFactory = getSessionFactory();

    public static void main(String[] args) {
        JpaDemo jpaDemo = new JpaDemo();
        jpaDemo.createUser();
        jpaDemo.getAllUsers();
    }

    private EntityManagerFactory getSessionFactory() {
        return Persistence.createEntityManagerFactory("hibernate.jpa");
    }

    private void createUser() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        User user = new User();
        user.setName("JPA user " + new Random().nextInt());
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    private void getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<User> result = entityManager.createQuery("from User", User.class).getResultList();
        for (User event : result) {
            System.out.println("User (" + event.getId() + ") : " + event.getName());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
