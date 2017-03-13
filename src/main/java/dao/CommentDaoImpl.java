package dao;

import entities.Book;
import entities.Comment;
import entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by maxbacinskiy on 10.03.17.
 */
@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Comment> getCommentOfBook(Book book) {
        return sessionFactory.getCurrentSession().createQuery("from Comment where book=:book")
                .setParameter("book", book).list();
    }

    @Transactional
    public Comment getCommentByBookAndUser(Book book, User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Comment where book=:book and author=:user");
        query.setParameter("book", book).setParameter("user", user);
        if (query.getResultList().size() == 0) {
            return null;
        } else {
            return (Comment) query.getSingleResult();
        }

    }

    @Transactional
    public void deleteComment(Comment comment) {
        sessionFactory.getCurrentSession().delete(comment);
    }

    @Transactional
    public Comment getCommentById(int id) {
        return sessionFactory.getCurrentSession().get(Comment.class, id);
    }

    @Transactional
    public void update(Comment comment) {
        sessionFactory.getCurrentSession().update(comment);
    }


}
