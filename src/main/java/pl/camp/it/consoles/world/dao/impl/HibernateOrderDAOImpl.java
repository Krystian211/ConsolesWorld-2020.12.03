package pl.camp.it.consoles.world.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.consoles.world.dao.IOrderDAO;
import pl.camp.it.consoles.world.model.Order;

import java.util.List;

@Repository
public class HibernateOrderDAOImpl implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistOrder(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Order> getOrdersByUser(int userId) {
        Session session=sessionFactory.openSession();
        Query<Order> query=session.createQuery("FROM pl.camp.it.consoles.world.model.Order WHERE user_id=:user_id");
        query.setParameter("user_id",userId);
        List<Order> orders=query.getResultList();
        session.close();
        return orders;
    }
}
