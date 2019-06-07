package me.kupchenko.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Random;

/**
 * Hello world!
 */
public class HibernateDemo {

    public static void main(String[] args) {
        HibernateDemo hibernateDemo = new HibernateDemo();
        SessionFactory sessionFactory = hibernateDemo.getSessionFactory();
        hibernateDemo.createUser(sessionFactory);
        hibernateDemo.getAllUsers(sessionFactory);
    }

    private SessionFactory getSessionFactory() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(HibernateUser.class);
        ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        return config.buildSessionFactory(servReg);
    }

    private void createUser(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        HibernateUser user = new HibernateUser();
        user.setName("Hibernate user " + new Random().nextInt());
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    private void getAllUsers(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<HibernateUser> result = session.createQuery("FROM HibernateUser ", HibernateUser.class).list();
        for (HibernateUser event : result) {
            System.out.println("User (" + event.getId() + ") : " + event.getName());
        }
        session.getTransaction().commit();
        session.close();
    }
}
