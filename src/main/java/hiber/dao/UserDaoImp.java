package hiber.dao;

import hiber.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE id = :id")
                .setParameter("id",
                        sessionFactory.getCurrentSession()
                        .createQuery("SELECT id FROM Car WHERE (model = :model AND series = :series)")
                        .setParameter("model", model)
                        .setParameter("series", series)
                        .uniqueResult())
                .uniqueResult();
    }
}
