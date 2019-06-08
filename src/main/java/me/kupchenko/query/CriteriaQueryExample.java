package me.kupchenko.query;

import me.kupchenko.jpa.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaQueryExample {

    public static void main(String[] args) {
        CriteriaQueryExample criteriaQueryExample = new CriteriaQueryExample();
        criteriaQueryExample.queryUsers();
        criteriaQueryExample.update();
    }

    public void queryUsers() {
        EntityManager entityManager = getSessionFactory().createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> from = query.from(User.class);
        query.where(cb.like(from.get("name"), "%Hibernate%"));
        TypedQuery<User> q = entityManager.createQuery(query);
        List<User> resultList = q.getResultList();
        resultList.forEach(System.out::println);
    }

    public void update() {
        EntityManager entityManager = getSessionFactory().createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<User> query = cb.createCriteriaUpdate(User.class);
        Root<User> from = query.from(User.class);
        query.where(cb.equal(from.get("id"), "7"));
        Query q = entityManager.createQuery(query);
        int updatedCount = q.executeUpdate();
        System.out.println("Updated rows" + updatedCount);
    }

    public void create() {
        //All possible modes of Criteria (SELECT, UPDATE, DELETE)
    }

    public void delete() {
        EntityManager entityManager = getSessionFactory().createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<User> query = cb.createCriteriaDelete(User.class);
        Root<User> from = query.from(User.class);
        query.where(cb.equal(from.get("id"), "7"));
        Query q = entityManager.createQuery(query);
        int updatedCount = q.executeUpdate();
        System.out.println("Updated rows" + updatedCount);
    }

    private EntityManagerFactory getSessionFactory() {
        return Persistence.createEntityManagerFactory("hibernate.jpa");
    }
}
