package com.workintech.Services;

import com.workintech.Enum.BookCategories;
import com.workintech.Model.Author;
import com.workintech.Model.Book;
import com.workintech.Model.Librarian;
import com.workintech.Model.User;

import java.util.List;
import java.util.Scanner;

public interface BookServices {
    void addBook(Librarian librarian);
    void updateBook(String id, Scanner scanner);
    void removeBook(Librarian librarian);
    Book getBookById(String id);
    List<Book> getBookByName(String name);
    List<Book> getBookByAuthor(String authorName);
    List<Book> getBooksByCategory(String category);
        boolean borrowBook(User user, String idD);
    boolean  returnBook(User user, String id);



}
