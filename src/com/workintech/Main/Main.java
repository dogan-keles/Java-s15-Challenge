package com.workintech.Main;

import com.workintech.Enum.BookCategories;
import com.workintech.Model.*;

import java.util.*;

public class Main {
    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Librarian> librarians = new HashMap<>();
    private User currentUser;
    private Librarian currentLibrarian;
    public static void main(String[] args) {
        Library library = new Library(new HashSet<>(), new HashMap<>(), 1000);
        Scanner scanner = new Scanner(System.in);

        User user = null;
        Librarian librarian = null;




        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Register as User");
            System.out.println("2. Register as Librarian");
            System.out.println("3. User Login");
            System.out.println("4. Librarian Login");
            System.out.println("5. Browse Books");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Add Book (Librarian Only)");
            System.out.println("9. Remove Book (Librarian Only)");
            System.out.println("10. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter your name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter your id:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    user = new User(name, 0, id, "", "", "", 5, new ArrayList<>(), 1000);
                    System.out.println("User registration successful!");
                    break;
                case 2:
                    System.out.println("Enter librarian name:");
                    String librarianName = scanner.nextLine();
                    System.out.println("Enter librarian id:");
                    int librarianId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter librarian password:");
                    String password = scanner.nextLine();
                    librarian = new Librarian(librarianName, 0, librarianId, "", "", "", password);
                    System.out.println("Librarian registration successful!");
                    break;
                case 3:
                    if (user == null) {
                        System.out.println("Please register as a user first.");
                    } else if (user != null && !user.isLoggedIn()) {
                        System.out.println("Enter your name:");
                        String enteredName = scanner.nextLine();
                        System.out.println("Enter your id:");
                        int enteredId = scanner.nextInt();
                        scanner.nextLine();
                        if (user.authenticate(enteredName, enteredId)) {
                            System.out.println("User login successful!");
                        } else {
                            System.out.println("User login failed! Incorrect username or ID.");
                        }
                    } else {
                        System.out.println("User is already logged in.");
                    }
                    break;
                case 4:
                    if (librarian == null) {
                        System.out.println("Please register as a librarian first.");
                    } else if (librarian != null && !librarian.isLoggedIn()) {
                        System.out.println("Enter librarian name:");
                        String enteredName = scanner.nextLine();
                        System.out.println("Enter librarian id:");
                        int enteredId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter librarian password:");
                        String enteredPassword = scanner.nextLine();
                        if (librarian.authenticate(enteredName, enteredPassword)) {
                            System.out.println("Librarian login successful!");
                        } else {
                            System.out.println("Librarian login failed! Incorrect username, ID, or password.");
                        }
                    } else {
                        System.out.println("Librarian is already logged in.");
                    }
                    break;
                case 5:
                    if (user != null && user.isLoggedIn()) {
                        List<Book> books = library.getBooksByCategory(BookCategories.MYSTERY);
                        for (Book book : books) {
                            System.out.println(book);
                        }
                    } else {
                        System.out.println("Please log in as a user first.");
                    }
                    break;
                case 6:
                    if (user != null && user.isLoggedIn()) {
                        System.out.println("Enter the ID of the book you want to borrow:");
                        String bookId = scanner.nextLine();
                        boolean success = library.borrowBook(user, bookId);
                        if (success) {
                            System.out.println("Book borrowed successfully!");
                        } else {
                            System.out.println("Book borrowing failed.");
                        }
                    } else {
                        System.out.println("Please log in as a user first.");
                    }
                    break;
                case 7:
                    if (user != null && user.isLoggedIn()) {
                        System.out.println("Enter the ID of the book you want to return:");
                        String bookId = scanner.nextLine();
                        boolean success = library.returnBook(user, bookId);
                        if (success) {
                            System.out.println("Book returned successfully!");
                        } else {
                            System.out.println("Book return failed.");
                        }
                    } else {
                        System.out.println("Please log in as a user first.");
                    }
                    break;
                case 8:
                    if (librarian != null && librarian.isLoggedIn()) {
                        // addBook metodu eklencek.
                    } else {
                        System.out.println("Please log in as a librarian first.");
                    }
                    break;
                case 9:
                    if (librarian != null && librarian.isLoggedIn()) {
                        // RemoveBook metodu eklencek.
                    } else {
                        System.out.println("Please log in as a librarian first.");
                    }
                    break;
                case 10:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
        public void registerUser(Scanner scanner){
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            System.out.println("Enter your id: ");
            int reader_id = scanner.nextInt();
            scanner.nextLine();
            User newUser = new User();

        }
    }
