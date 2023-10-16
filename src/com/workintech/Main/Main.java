package com.workintech.Main;

import com.workintech.Enum.BookCategories;
import com.workintech.Model.*;

import java.util.*;

public class Main {
    private static Map<Integer, User> users = new HashMap<>();
    //private static Map<Integer, Librarian> librarians = new HashMap<>();
    private static User currentUser;
    private static Librarian currentLibrarian;

    public static void main(String[] args) {
        Library library = new Library(new HashSet<>(), new HashMap<>(), 1000);
        Scanner scanner = new Scanner(System.in);
        Librarian librarian = new Librarian("dogan", 29, 0121, "beyoglu", "5054296747", "dgnkls.47@gmail.com", "0121abc");

        User user = null;


        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Register as User");
            System.out.println("2. User Login");
            System.out.println("3. Librarian Login");
            System.out.println("4. Browse Books");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Add Book (Librarian Only)");
            System.out.println("8. Remove Book (Librarian Only)");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    user = loginUser(scanner);
                    break;
                case 3:
                    librarian = loginLibrarian(scanner, librarian);
                    break;
                case 4:
                    if (user != null && user.isLoggedIn()) {
                        List<Book> books = library.getBooksByCategory(BookCategories.MYSTERY);
                        for (Book book : books) {
                            System.out.println(book);
                        }
                    } else {
                        System.out.println("Please log in as a user first.");
                    }
                    break;
                case 5:
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
                case 6:
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
                case 7:
                    if (librarian != null && librarian.isLoggedIn()) {
                        // addBook metodu eklenecek.
                    } else {
                        System.out.println("Please log in as a librarian first.");
                    }
                    break;
                case 8:
                    if (librarian != null && librarian.isLoggedIn()) {
                        // RemoveBook metodu eklenecek.
                    } else {
                        System.out.println("Please log in as a librarian first.");
                    }
                    break;
                case 9:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void registerUser(Scanner scanner) {
        String name = null;
        int reader_id = 0;
        String email = null;

        while (true) {
            try {
                System.out.println("Enter your name: ");
                name = scanner.nextLine();

                if (!name.matches("^[a-zA-Z\\s]+$")) {
                    throw new IllegalArgumentException("Invalid name. Please enter a valid name.");
                }

                System.out.println("Enter your id: ");
                String idInput = scanner.nextLine();
                reader_id = Integer.parseInt(idInput);

                if (reader_id <= 0) {
                    throw new NumberFormatException("Invalid ID. Please enter a valid ID.");
                }

                System.out.println("Enter your email: ");
                email = scanner.nextLine();

                if (!email.contains("@")) {
                    throw new IllegalArgumentException("Invalid email. Please enter a valid email address.");
                }

                break;

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        User newUser = new User(name, 0, reader_id, "", "", email, 5, new ArrayList<>(), 1000);
        users.put(reader_id, newUser);
        System.out.println("User registration successful!");
    }

    public static User loginUser(Scanner scanner) {
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your id: ");
        int reader_id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        User user = users.get(reader_id);
        if (user != null && user.getName().equals(name) && user.getEmail().equals(email)) {
            System.out.println("User login successful!");
            user.setLoggedIn(true);

            return user;
        } else {
            System.out.println("User login failed! Incorrect username, ID, or email.");
            return null;
        }
    }

    public static Librarian loginLibrarian(Scanner scanner, Librarian librarian) {
        System.out.println("Enter your name: ");
        String enteredUsername = scanner.nextLine();
        System.out.println("Enter your password: ");
        String enteredPassword = scanner.nextLine();

      // Assuming librarian has ID 1
        if (librarian != null && librarian.getName().equals(enteredUsername) && librarian.getPassword().equals(enteredPassword)) {
            System.out.println("Librarian login successful!");
            librarian.setLoggedIn(true);
            return librarian;
        } else {
            System.out.println("Librarian login failed! Incorrect username or password.");
            return null;
        }
    }
}
