package com.workintech.Model;

import com.workintech.Services.BookServices;
import com.workintech.Services.UserServices;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class User extends Person {
private int borrowedBooksLimit;
private List<Book> borrowedBooks;
private double balance;
private boolean isLoggedIn = false;


    public User(String name, int age, int reader_id, String adress, String phoneNumber, String email, int borrowedBooksLimit, List<Book> borrowedBooks, double balance) {
        super(name, age, reader_id, adress, phoneNumber, email);
        this.borrowedBooksLimit = borrowedBooksLimit;
        this.borrowedBooks = borrowedBooks;
        this.balance = balance;
    }

    public boolean authenticate(String enteredName, int enteredId) {
        if (this.getName().equals(enteredName) && this.getReader_id() == enteredId) {
            isLoggedIn = true;
            return true;
        } else {
            isLoggedIn = false;
            return false;
        }
    }


    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public int getBorrowedBooksLimit() {
        return borrowedBooksLimit;
    }

    public void setBorrowedBooksLimit(int borrowedBooksLimit) {
        this.borrowedBooksLimit = borrowedBooksLimit;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setBorrowed(false);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "borrowedBooksLimit=" + borrowedBooksLimit +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return borrowedBooksLimit == user.borrowedBooksLimit && Objects.equals(borrowedBooks, user.borrowedBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), borrowedBooksLimit, borrowedBooks);
    }
}
