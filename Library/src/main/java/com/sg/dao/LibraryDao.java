package com.sg.dao;

import com.sg.model.Author;
import com.sg.model.Book;
import com.sg.model.Publisher;

import java.util.List;

public interface LibraryDao {

    public void addAuthor(Author author);

    public void deleteAuthor(int authorId);

    public void updateAuthor(Author author);

    public Author getAuthorById(int id);

    public List<Author> getAllAuthors();

    public void addBook(Book book);

    public void deleteBook(int bookId);

    public void updateBook(Book book);

    public Book getBookById(int id);

    //author can write one or more books (many to many)
    public List<Book> getBooksByAuthorId(int authorId);

    //publisher can publish more than one book (1 to many)
    public List<Book> getBooksByPublisherId(int publisherId);

    public List<Book> getAllBooks();

    public void addPublisher(Publisher publisher);

    public void deletePublisher(int publisherId);

    public void updatePublisher(Publisher publisher);

    public Publisher getPublisherById(int id);

    public List<Publisher> getAllPublishers();
}

