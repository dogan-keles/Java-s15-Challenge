    package com.workintech.Model;

    import com.workintech.Enum.BookCategories;
    import com.workintech.Services.BookServices;

    import java.util.*;

    public class Library implements BookServices {

        private Set<String> borrowedBooks;
        private Map<String, Book> bookList;
        private double balance;


        public Library(Set<String> borrowedBooks, Map<String, Book> bookList, double balance) {
                      this.borrowedBooks = new HashSet<>();
                      this.bookList = new HashMap<>();
                      this.balance = balance;
        }

        public boolean borrowBook(User user, String id) {
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
                balance -= book.getPrice();
                System.out.println("You borrowed this book: " + book.getName());
                return true;
            }
        }

        public void addBook(Book book){

            if(!bookList.containsKey(book.getId())) {
                bookList.put(book.getId(), book);
                System.out.println("The book is added successfully.");
            } else {
                System.out.println("The book for this ID already exists.");
            }
        }

        public void removeBook(String id){
             if(bookList.containsKey(id)){
                 bookList.remove(id);
                 System.out.println("The book is removed from list: " );

             }
        }
        public void updateBook(String id, Book newBookInfo) {
            if (bookList.containsKey(id)) {
                bookList.put(id, newBookInfo);
            }
        }
        public Book getBookById(String id) {
            if (bookList.containsKey(id)) {
                Book book = bookList.get(id);
                if (book.getId().equals(id)) {
                    return book;
                }
            }
            System.out.println("The book is not found, try again.");
            return null;
        }


        public List<Book> getBookByName(String name) {
            List<Book> foundBooks = new ArrayList<>();
            for (Book book : bookList.values()) {
                if (book.getName().equalsIgnoreCase(name)) {
                    foundBooks.add(book);
                }
            }
            if(foundBooks.isEmpty()){
                System.out.println("The book is not found.");
            }

            return foundBooks;
        }

        public List<Book> getBookByAuthor(Author author) {
            List<Book> booksByAuthor = new ArrayList<>();

            for (Book book : bookList.values()) {
                if (book.getAuthor().getName().equalsIgnoreCase(author.getName()) &&
                        book.getAuthor().getSurname().equalsIgnoreCase(author.getSurname())) {
                    booksByAuthor.add(book);
                }
            }

            if (booksByAuthor.isEmpty()) {
                System.out.println("Author info is not found.");
            }

            return booksByAuthor;
        }
        public  List <Book> getBooksByCategory(BookCategories categories){
            List<Book> booksByCategories = new ArrayList<>();
            for (Book book: bookList.values()) {
                if(book.getBookCategories().equals(categories)){
                    booksByCategories.add(book);
                }
            }
            if(booksByCategories.isEmpty()) {
                System.out.println("No books belonging to the specified category were found.");

            }
            return booksByCategories;
        }

        public boolean returnBook(User user, String id) {
            Book book = getBookById(id);

            if (user == null || book == null) {
                System.out.println("Invalid user or book.");
                return false;
            }

            if (user.getBorrowedBooks().contains(book)) {
                user.returnBook(book);
                book.setBorrowed(false);
                user.setBalance(user.getBalance() + book.getPrice()); // Kitabın ücreti kadar balance artırıldı
                System.out.println("Book returned successfully.");
                return true;
            } else {
                System.out.println("The book is not borrowed by the user.");
                return false;
            }
        }










    }