package me.kupchenko;

import me.kupchenko.hibernate.HibernateUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.hibernate.service.ServiceRegistry;

public class TransactionsHibernate {
    public static void main(String[] args) {
        SessionFactory sessionFactory = getSessionFactory();

        Session session = sessionFactory.openSession();
        try {
            // calls Connection#setAutoCommit( false ) to
            // signal start of transaction
            session.getTransaction().begin();

            session.createQuery("UPDATE User set name = 'Sir. ' ")
                    .executeUpdate();

            // calls Connection#commit(), if an error
            // happens we attempt a rollback
            session.getTransaction().commit();
        } catch (Exception e) {
            // we may need to rollback depending on
            // where the exception happened
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
            // handle the underlying error
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static SessionFactory getSessionFactory() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(HibernateUser.class);
        ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        return config.buildSessionFactory(servReg);
    }
}
