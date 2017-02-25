package web;

import entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.BookService;

import java.util.Map;

/**
 * Created by maxbacinskiy on 23.02.17.
 */

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping("/")
    public String redirect(Map<String, Object> map) {
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Map<String, Object> map) {
        map.put("books", service.getAllBooks());
        map.put("bookEx", new Book());
        return "home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String newBook(@ModelAttribute("bookEx") Book book, BindingResult result) {
        service.addBook(book);
        return "redirect:/home";
    }

    @RequestMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") Integer bookId) {
        service.deleteBook(bookId);
        return "redirect:/home";
    }

    @RequestMapping("/read/{bookId}")
    public String readBook(@PathVariable("bookId") Integer bookId, Map<String, Object> map) {
        map.put("book", service.getBook(bookId));
        return "description";
    }



}
