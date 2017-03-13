package entities;

import javax.persistence.*;

/**
 * Created by maxbacinskiy on 23.02.17.
 */
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private int id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_author")
    private String author;

    @Column(name = "book_rating")
    private int rating;

    @Column(name = "book_description")
    private String text;

    public Book() {
    }

    public Book(String name, String author, int rating, String text) {
        this.name = name;
        this.author = author;
        this.rating = rating;
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String description) {
        this.text = description;
    }
}
