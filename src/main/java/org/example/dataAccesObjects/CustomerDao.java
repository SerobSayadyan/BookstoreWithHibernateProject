package org.example.dataAccesObjects;

import org.example.HibernateConfig;
import org.example.model.Book;
import org.example.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao {

    private static final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    public Customer getById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Customer.class, id);
        }
    }

    public List<Customer> getAllCustomers() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Customer";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            return query.list();
        }
    }

    public void update(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void save(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



}
