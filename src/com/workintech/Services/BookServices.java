package com.workintech.Services;

import com.workintech.Enum.BookCategories;
import com.workintech.Model.Author;
import com.workintech.Model.Book;
import com.workintech.Model.User;

import java.util.List;

public interface BookServices {
    void addBook(Book book);
    void updateBook(String id, Book newBookInfo);
    void removeBook(String id);
    Book getBookById(String id);
    List<Book> getBookByName(String name);
    List<Book> getBookByAuthor(Author author);
    List<Book> getBooksByCategory(BookCategories categories);
        boolean borrowBook(User user, String idD);
    boolean  returnBook(User user, String id);



}
