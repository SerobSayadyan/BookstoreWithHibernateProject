package org.example.dataAccesObjects;

import org.example.HibernateConfig;
import org.example.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDao {

    private static final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    public Book getById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Book.class, id);
        }
    }

    public List<Book> getAllBooks() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Book";
            Query<Book> query = session.createQuery(hql, Book.class);
            return query.list();
        }
    }

    public List<Book> getByAuthor(String author) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Book WHERE author = :author ORDER BY author";
            Query<Book> query = session.createQuery(hql, Book.class);
            query.setParameter("author", author);
            return query.list();
        }
    }

    public List<Book> getByGenre(String genre) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Book WHERE genre = :genre ORDER BY genre";
            Query<Book> query = session.createQuery(hql, Book.class);
            query.setParameter("genre", genre);
            return query.list();
        }
    }

    public void update(Book book) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void save(Book book) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



}
