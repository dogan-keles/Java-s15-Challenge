package com.workintech.Model;

import com.workintech.Enum.BookCategories;

import java.util.Objects;

public class Book{
private String id;
private Author author;
private String name;
private boolean isBorrowed;
private BookCategories bookCategories;


    public Book(String id, Author author, String name, boolean isBorrowed, BookCategories bookCategories) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.isBorrowed = isBorrowed;
               this.bookCategories = bookCategories;
    }

    public String getId() {
        return id;
    }





    public void setId(String id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public BookCategories getBookCategories() {
        return bookCategories;
    }

    public void setBookCategories(BookCategories bookCategories) {
        this.bookCategories = bookCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", isBorrowed=" + isBorrowed +
                ", bookCategories=" + bookCategories +
                '}';
    }
}
