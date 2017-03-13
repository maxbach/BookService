package web;

import entities.Book;
import entities.Comment;
import entities.Role;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.BookService;
import service.CommentService;
import service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by maxbacinskiy on 23.02.17.
 */

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String redirect(Map<String, Object> map) {
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Map<String, Object> map) {
        map.put("books", bookService.getAllBooks());
        map.put("bookEx", new Book());
        return "home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String newBook(@ModelAttribute("bookEx") Book book, BindingResult result) {
        bookService.addBook(book);
        return "redirect:/home";
    }

    @RequestMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") Integer bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/home";
    }

    @RequestMapping("/read/{bookId}")
    public String readBook(@PathVariable("bookId") Integer id, Map<String, Object> map, Authentication authentication) {

        Book book = bookService.getBook(id);
        map.put("book", book);
        List<Comment> comments = commentService.getCommentOfBook(book);

        if (authentication != null) {
            User user = userService.getUserByLogin(authentication.getName());
            Comment comment = commentService.getCommentByBookAndUser(book, user);
            if (comment != null) {
                map.put("myComment", comment);
                comments.remove(comment);
            }
            map.put("comment", new Comment());
        }

        map.put("comments", comments);
        return "description";
    }

    @RequestMapping("/read/{bookId}/deleteComment/{commentId}")
    public String deleteComment(@PathVariable("commentId") Integer commentId, @PathVariable("bookId") Integer bookId) {
        commentService.deleteComment(commentService.getCommentById(commentId));
        return "redirect:/read/" + bookId;
    }

    @RequestMapping(value = "/read/{bookId}/newComment", method = RequestMethod.POST)
    public String addNewComment(@PathVariable("bookId") Integer bookId, @ModelAttribute("comment") Comment comment,
                                Authentication authentication) {
        comment.setBook(bookService.getBook(bookId));
        String username = authentication.getName();
        comment.setAuthor(userService.getUserByLogin(username));
        commentService.addComment(comment);
        return "redirect:/read/" + bookId;
    }

    @RequestMapping("/read/{bookId}/removeSelfComment")
    public String removeSelfComment(@PathVariable("bookId") Integer bookId, Authentication authentication) {
        User nowUser = userService.getUserByLogin(authentication.getName());
        Book nowBook = bookService.getBook(bookId);
        Comment testComment = commentService.getCommentByBookAndUser(nowBook, nowUser);
        if (testComment != null) {
            commentService.deleteComment(testComment);
        }
        return "redirect:/read/" + bookId;
    }

    @RequestMapping(value = "/edit/{objId}", method = RequestMethod.GET)
    public String getEditingObj(@PathVariable(name = "objId") Integer objId, Map<String, Object> map, Authentication authentication) {
        User nowUser = userService.getUserByLogin(authentication.getName());
        map.put("objId", objId);
        if (nowUser.getRole() == Role.ADMIN) {
            map.put("editingBook", bookService.getBook(objId));
            return "editB";
        } else if (nowUser.getRole() == Role.USER) {
            map.put("editingComment", commentService.getCommentById(objId));
            return "editC";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/editB/{bookId}", method = RequestMethod.POST)
    public String editBook(@ModelAttribute(name = "editingBook") Book book, @PathVariable("bookId") Integer id) {

        book.setId(id);
        bookService.updateBook(book);
        return "redirect:/home";
    }

    @RequestMapping(value = "/editC/{commentID}", method = RequestMethod.POST)
    public String editComment(@PathVariable("commentID") Integer id,
                              @ModelAttribute(name = "editingComment") Comment comment, Authentication authentication) {
        comment.setId(id);
        Book book = commentService.getCommentById(id).getBook();
        comment.setBook(book);
        comment.setAuthor(userService.getUserByLogin(authentication.getName()));
        commentService.updateComment(comment);
        return "redirect:/read/" + comment.getBook().getId();
    }


}
