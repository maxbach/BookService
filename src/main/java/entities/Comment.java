package entities;

import javax.persistence.*;

/**
 * Created by maxbacinskiy on 10.03.17.
 */
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "comment_book")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "comment_author")
    private User author;

    @Column(name = "comment_text")
    private String text;

    @Column(name = "comment_rating")
    private int rating;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
