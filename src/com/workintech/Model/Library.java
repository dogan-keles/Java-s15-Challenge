package com.workintech.Model;

import com.workintech.Enum.BookCategories;
import com.workintech.Services.BookServices;

import java.util.*;

public class Library implements BookServices {

    private Set<String> borrowedBooks;
    private Map<String, Book> bookList;
    private double balance;

    public Set<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public Map<String, Book> getBookList() {
        return bookList;
    }

    public double getBalance() {
        return balance;
    }

    public Library(Set<String> borrowedBooks, Map<String, Book> bookList, double balance) {
        this.borrowedBooks = borrowedBooks;
        this.bookList = bookList;
        this.balance = balance;
    }

    public boolean borrowBook(User user, String id) {
        if (user != null && user.isLoggedIn()) {
            Book book = getBookById(id);
            if (borrowedBooks.size() >= 5) {
                System.out.println("We're sorry, you've exceeded your book borrowing limit.");
                return false;
            } else if (borrowedBooks.contains(id)) {
                System.out.println("You have already borrowed this book.");
                return false;
            } else if (book.isBorrowed()) {
                System.out.println("This book is already borrowed by someone else.");
                return false;
            } else {
                borrowedBooks.add(id);
                book.setBorrowed(true);
                user.setBalance(user.getBalance() - 50);

                System.out.println("You borrowed this book: " + book.getName() + " ||  Your Balance: " + user.getBalance() + "TL");
                return true;
            }
        }
        return false;
    }

    public void addBook(Librarian librarian) {
        if (librarian != null && librarian.isLoggedIn()) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Enter the book ID: ");
                String bookId = scanner.nextLine();

                if (bookList.containsKey(bookId)) {
                    System.out.println("A book with this ID already exists.");
                    return;
                }

                System.out.println("Enter the book title: ");
                String title = scanner.nextLine();

                System.out.println("Is the book borrowed? (true/false): ");
                boolean isBorrowed = scanner.nextBoolean();
                scanner.nextLine();

                System.out.println("These are book categories: " + Arrays.toString(BookCategories.values()));
                System.out.println("Please enter a book category (Upper Case Only!) : ");
                String categoryInput = scanner.nextLine();

                if (!isValidCategory(categoryInput)) {
                    System.out.println("Invalid category input. Please enter a valid category.");
                    return;
                }
                BookCategories category = BookCategories.valueOf(categoryInput);

                System.out.println("Enter the author's name: ");
                String authorName = scanner.nextLine();

                System.out.println("Enter the author's ID: ");
                String authorId = scanner.nextLine();

                Author author = new Author(authorName, authorId, new ArrayList<>());

                Book newBook = new Book(bookId, author, title, isBorrowed, category);

                bookList.put(newBook.getId(), newBook);
                System.out.println("Book added successfully: " + newBook.getAuthor() + " - " + newBook.getName());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the correct data type.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        } else {
            System.out.println("Only librarians can add books. Please log in as a librarian.");
        }
    }


    private boolean isValidCategory(String categoryInput) {
        for (BookCategories category : BookCategories.values()) {
            if (category.name().equals(categoryInput)) {
                return true;
            }
        }
        return false;
    }

    public void removeBook(Librarian librarian) {
        if (librarian != null && librarian.isLoggedIn()) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Please enter the book ID you want to remove from the database.");
                String bookId = scanner.nextLine();
                if (bookList.containsKey(bookId)) {
                    Book removedBook = bookList.remove(bookId);

                    if (removedBook != null) {
                        System.out.println("The book is removed from the library database. " + removedBook.getAuthor() + " - " + removedBook.getName());
                    } else {
                        System.out.println("Error: The book was not removed from the library database.");
                    }
                } else {
                    System.out.println("No book was found with the ID you entered. Please enter a book ID that you are sure of.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the correct data type.");
                scanner.nextLine();
            }
        } else {
            System.out.println("Only librarians can remove books. If you are a librarian, please log in as a librarian.");
        }
    }









    public void updateBook(String id, Scanner scanner) {
        if (bookList.containsKey(id)) {
            System.out.println("Please enter the new title for the book:");
            String newTitle = scanner.nextLine();

            System.out.println("Is the book borrowed? (true/false):");
            boolean isBorrowed = scanner.nextBoolean();
            scanner.nextLine();

            System.out.println("Please enter the new category for the book:");
            String categoryInput = scanner.nextLine().toUpperCase();

            if (!isValidCategory(categoryInput)) {
                System.out.println("Invalid category input. Please enter a valid category.");
                return;
            }
            BookCategories newCategory = BookCategories.valueOf(categoryInput);

            System.out.println("Please enter the new author's name:");
            String newAuthorName = scanner.nextLine();

            System.out.println("Please enter the new author's ID:");
            String newAuthorId = scanner.nextLine();

            Author newAuthor = new Author(newAuthorName, newAuthorId, new ArrayList<>());

            Book updatedBook = new Book(id, newAuthor, newTitle, isBorrowed, newCategory);
            bookList.put(id, updatedBook);
            System.out.println("Book updated successfully: " + updatedBook.getAuthor() + " - " + updatedBook.getName());
        } else {
            System.out.println("No book found with the ID you entered. Please enter a valid book ID.");
        }
    }


    public Book getBookById(String id) {
        if (bookList.containsKey(id)) {
            Book foundBook = bookList.get(id);
            if (!foundBook.isBorrowed()) {
                System.out.println("Book is found: " + foundBook.getAuthor() + " - " + foundBook.getName() + "This book is not borrowed, you can borrow it.");
            } else {
                System.out.println("Book is found: " + foundBook.getAuthor() + " - " + foundBook.getName() + "This book is borrowed.");


            }
            return foundBook;
        }
        System.out.println("The book is not found, try again.");
        return null;
    }

    public List<Book> getBookByName(String name) {
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : bookList.values()) {
            if (book.getName().equalsIgnoreCase(name)) {
                foundBooks.add(book);
                System.out.println("Book is found: " + book.getAuthor() + " - " + book.getName());
            }
        }

        if (foundBooks.isEmpty()) {
            System.out.println("The book is not found.");
        }

        return foundBooks;
    }


    public List<Book> getBookByAuthor(String authorName) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : bookList.values()) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                booksByAuthor.add(book);
            }

        }
        if (booksByAuthor.isEmpty()) {
            System.out.println("Author info is not found.");
        }
        return booksByAuthor;
    }

    public List<Book> getBooksByCategory(String category) {
        List<Book> booksByCategories = new ArrayList<>();
        for (Book book : bookList.values()) {
            if (book.getBookCategories().name().equalsIgnoreCase(category)) {
                booksByCategories.add(book);
            }
        }
        if (booksByCategories.isEmpty()) {
            System.out.println("No books belonging to the specified category were found.");
        }
        return booksByCategories;
    }

    public boolean returnBook(User user, String id) {
        if (user == null) {
            System.out.println("Invalid user.");
            return false;
        }

        Book book = bookList.get(id);

        if (book == null) {
            System.out.println("Invalid book.");
            return false;
        }

        if (borrowedBooks.contains(id)) {
            user.returnBook(book);
            book.setBorrowed(false);
            user.setBalance(user.getBalance() + 50);
            System.out.println("Book returned successfully. || " + book.getName() + " || Your Balance: " + user.getBalance() + "TL");
            return true;
        } else {
            System.out.println("Book returning process failed");
            return false;
        }
    }

}
