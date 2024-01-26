package org.example.dataAccesObjects;

import org.example.HibernateConfig;
import org.example.model.Book;
import org.example.model.Customer;
import org.example.model.Sale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SaleDao {

    private static final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    public Sale getById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Sale.class, id);
        }
    }

    public List<Sale> getAllBooks() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Sale";
            Query<Sale> query = session.createQuery(hql, Sale.class);
            return query.list();
        }
    }

    public List<Sale> getByBook(Book book) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Sale WHERE book.id = :bookId";
            Query<Sale> query = session.createQuery(hql, Sale.class);
            query.setParameter("bookId", book.getBookId());
            return query.list();
        }
    }

    public List<Sale> getByCustomer(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Sale WHERE customer.id = :customerId";
            Query<Sale> query = session.createQuery(hql, Sale.class);
            query.setParameter("customerId", customer.getId());
            return query.list();
        }
    }


    public void update(Sale sale) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(sale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void save(Sale sale) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(sale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



}
