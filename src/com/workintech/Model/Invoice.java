package com.workintech.Model;

import com.workintech.Enum.BookFees;
import com.workintech.Services.InvoiceServices;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Invoice implements InvoiceServices {
    private static final Map<BookFees, Double> bookFeesMap = new HashMap<>();

    static {

        bookFeesMap.put(BookFees.LOW, 100.0);
        bookFeesMap.put(BookFees.MODERATE, 150.0);
        bookFeesMap.put(BookFees.HIGH, 200.0);
        bookFeesMap.put(BookFees.PREMIUM, 250.0);
        bookFeesMap.put(BookFees.FREE, 0.0);
    }

    private User user;
    private Book book;
    private double purchaseFee;

    public Invoice(User user, Book book, double purchaseFee) {
        this.user = user;
        this.book = book;
        this.purchaseFee = calculateBookPurchaseFee(book.getBookFees());
    }

    @Override
    public double calculateBookPurchaseFee(BookFees bookFees) {
        return bookFeesMap.getOrDefault(bookFees, 0.0);
    }

    @Override
    public Invoice createAnInvoice(User user, Book book, double purchaseFee) {
        return new Invoice(user, book, purchaseFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book, purchaseFee);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Invoice invoice = (Invoice) obj;
        return Double.compare(invoice.purchaseFee, purchaseFee) == 0 &&
                Objects.equals(user, invoice.user) &&
                Objects.equals(book, invoice.book);
    }
}
