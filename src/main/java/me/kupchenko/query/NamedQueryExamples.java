package me.kupchenko.query;

import me.kupchenko.jpa.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class NamedQueryExamples {
    public static void main(String[] args) {
        NamedQueryExamples namedQueryExamples = new NamedQueryExamples();
        EntityManagerFactory entityManagerFactory = NamedQueryExamples.entityManagerFactory();
        namedQueryExamples.queryUsers(entityManagerFactory);
    }

    private void queryUsers(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = entityManager.createNamedQuery("findAllUsersWithName", User.class);
        query.setParameter("custName", "%Hibernate%");
        List<User> resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }

    private static EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("hibernate.jpa");
    }
}
