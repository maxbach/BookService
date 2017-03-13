package dao;

import entities.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by maxbacinskiy on 23.02.17.
 */

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Book> getAllBooks() {
        return sessionFactory.getCurrentSession().createQuery("from Book").getResultList();
    }

    @Transactional
    public void addBook(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }


    @Transactional
    public void deleteBook(int id) {
        Book book = getBook(id);
        sessionFactory.getCurrentSession().delete(book);
    }

    @Transactional
    public Book getBook(int id) {
        return sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Transactional
    public void update(Book book) {
        sessionFactory.getCurrentSession().update(book);
    }
}
