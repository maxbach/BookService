package service;

import entities.Book;
import entities.Comment;
import entities.User;

import java.util.List;

/**
 * Created by maxbacinskiy on 10.03.17.
 */
public interface CommentService {
    void addComment(Comment comment);

    void deleteComment(Comment comment);

    void updateComment(Comment comment);

    List<Comment> getCommentOfBook(Book book);

    Comment getCommentByBookAndUser(Book book, User user);

    Comment getCommentById(int id);
}
