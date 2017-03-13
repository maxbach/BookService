package service;

import dao.BookDao;
import entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by maxbacinskiy on 23.02.17.
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    public void deleteBook(int id) {
        bookDao.deleteBook(id);
    }

    public void updateBook(Book book) {
        bookDao.update(book);
    }

    public Book getBook(int id) {
        return bookDao.getBook(id);
    }
}
