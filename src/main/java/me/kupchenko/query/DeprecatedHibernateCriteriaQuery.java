package me.kupchenko.query;

import me.kupchenko.jpa.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DeprecatedHibernateCriteriaQuery {

    private void select(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("name", "Mou%"));
        List list = criteria.list();
    }
}
