package service;

import entities.Book;

import java.util.List;

/**
 * Created by maxbacinskiy on 23.02.17.
 */
public interface BookService {

    List<Book> getAllBooks();

    void addBook(Book book);

    void deleteBook(int id);

    void updateBook(Book book);

    Book getBook(int id);

}
