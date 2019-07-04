package me.kupchenko.transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TransactionsJpa {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = entityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    private static EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("hibernate.jpa");
    }

}
