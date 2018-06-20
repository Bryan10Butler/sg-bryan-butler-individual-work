package com.sg.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Book {

    private int bookId;
    private String isbn;
    private String title;
    //our book can have a publisher (1 to Many)
    private Publisher publisher;
    //a book can have one or more authors (1 half of Many to Many; other half library dao)
    //This class Author class is related to Book by a many to many
    //Store authors related to the particular book
    private List<Author> authors;
    private BigDecimal price;
    private LocalDate publishDate;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getBookId() == book.getBookId() &&
                Objects.equals(getIsbn(), book.getIsbn()) &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getPublisher(), book.getPublisher()) &&
                Objects.equals(getAuthors(), book.getAuthors()) &&
                Objects.equals(getPrice(), book.getPrice()) &&
                Objects.equals(getPublishDate(), book.getPublishDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getBookId(), getIsbn(), getTitle(), getPublisher(), getAuthors(), getPrice(), getPublishDate());
    }
}
