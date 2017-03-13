package dao;

import entities.Book;

import java.util.List;

/**
 * Created by maxbacinskiy on 23.02.17.
 */
public interface BookDao {

    public List<Book> getAllBooks();
    public void addBook(Book book);
    public void deleteBook(int id);
    public Book getBook(int id);

    public void update(Book book);

}
