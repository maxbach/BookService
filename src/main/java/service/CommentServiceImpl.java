package service;

import dao.CommentDao;
import entities.Book;
import entities.Comment;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by maxbacinskiy on 10.03.17.
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    public void addComment(Comment comment) {
        commentDao.saveComment(comment);
    }

    public void deleteComment(Comment comment) {
        commentDao.deleteComment(comment);
    }

    public void updateComment(Comment comment) {
        commentDao.update(comment);
    }

    public List<Comment> getCommentOfBook(Book book) {
        return commentDao.getCommentOfBook(book);
    }

    public Comment getCommentByBookAndUser(Book book, User user) {
        return commentDao.getCommentByBookAndUser(book, user);
    }

    public Comment getCommentById(int id) {
        return commentDao.getCommentById(id);
    }
}
