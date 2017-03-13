package dao;

import entities.Book;
import entities.Comment;
import entities.User;

import java.util.List;

/**
 * Created by maxbacinskiy on 10.03.17.
 */
public interface CommentDao {
    void saveComment(Comment comment);

    List<Comment> getCommentOfBook(Book book);

    Comment getCommentByBookAndUser(Book book, User user);

    void deleteComment(Comment comment);

    Comment getCommentById(int id);

    void update(Comment comment);
}
