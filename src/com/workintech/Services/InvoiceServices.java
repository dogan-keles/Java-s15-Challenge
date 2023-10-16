package com.workintech.Services;

import com.workintech.Enum.BookFees;
import com.workintech.Model.Book;
import com.workintech.Model.Invoice;
import com.workintech.Model.User;

public interface InvoiceServices {
    double calculateBookPurchaseFee(BookFees bookFees);
    Invoice createAnInvoice(User user, Book book, double purchaseFee);
}
