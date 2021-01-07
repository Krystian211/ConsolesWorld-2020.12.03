package pl.camp.it.consoles.world.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.consoles.world.dao.IUserDAO;
import pl.camp.it.consoles.world.model.User;

@Repository
public class HibernateUserDAOImpl implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUserByLogin(String login) {
        Session session=sessionFactory.openSession();
        Query<User> query=session.createQuery("FROM pl.camp.it.consoles.world.model.User WHERE login=:login");
        query.setParameter("login",login);
        User user=null;
        try {
            user=query.getSingleResult();
        } catch (Exception e) {
            System.out.println("No user found!");
        }
        session.close();
        return user;
    }

    @Override
    public void persistUser(User user) {
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction!=null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction!=null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
