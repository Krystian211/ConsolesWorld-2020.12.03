package pl.camp.it.consoles.world.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.consoles.world.dao.IProductDAO;
import pl.camp.it.consoles.world.model.Product;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class HibernateProductDAOImpl implements IProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Product> getProductsByCategory(Product.Category category) {
        Session session=this.sessionFactory.openSession();
        Query<Product> query=session.createQuery("FROM pl.camp.it.consoles.world.model.Product where category=:category");
        query.setParameter("category",category);
        List<Product> products=query.getResultList();
        session.close();
        return products;
    }

    @Override
    public Product getProductByManufacturerCode(String manufacturerCode) {
        Session session=sessionFactory.openSession();
        Query<Product> query=session.createQuery("From pl.camp.it.consoles.world.model.Product where manufacturerCode=:manufacturerCode");
        query.setParameter("manufacturerCode",manufacturerCode);
        Product product=null;
        try {
            product=query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No product found!");;
        }
        session.close();
        return product;
    }

    @Override
    public Product getProductById(int productId) {
        Session session=sessionFactory.openSession();
        Query<Product> query=session.createQuery("FROM pl.camp.it.consoles.world.model.Product WHERE id=:productId");
        query.setParameter("productId",productId);
        Product product=null;
        try {
            product=query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No product found!");
        }
        session.close();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        Session session=sessionFactory.openSession();
        Query<Product> query=session.createQuery("FROM pl.camp.it.consoles.world.model.Product");
        List<Product> products=query.getResultList();
        session.close();
        return products;
    }

    @Override
    public void persistProduct(Product product) {
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            session.save(product);
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
    public void updateProduct(Product product) {
    Session session=sessionFactory.openSession();
    Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            session.update(product);
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
