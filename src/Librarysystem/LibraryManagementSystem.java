package Librarysystem;

import java.util.Comparator;
import java.util.List;
//import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library(); // Creating a new Library instance
        Scanner scanner = new Scanner(System.in);
        
        // Localization setup
        Locale currentLocale = Locale.getDefault(); // Getting the default locale
        ResourceBundle messages = ResourceBundle.getBundle("Resources.Messages_eng", currentLocale); // Loading localized messages
        System.out.println(messages.getString("menu.title"));
        
        while (true) {
        	 // Display the title from the resource bundle
//            System.out.println(messages.getString("menu.title"));
            
            // Display menu options based on the selected language
            System.out.println(messages.getString("menu.addBook"));
            System.out.println(messages.getString("menu.removeBook"));
            System.out.println(messages.getString("menu.showBooks"));
            System.out.println(messages.getString("menu.updateBook"));
            System.out.println(messages.getString("menu.submitFeedback"));
            System.out.println(messages.getString("menu.viewFeedback"));
            System.out.println(messages.getString("menu.countBooks"));
            System.out.println(messages.getString("menu.changeLanguage"));
            System.out.println(messages.getString("menu.sortBooks")); 
            System.out.println(messages.getString("menu.exit"));
            System.out.print(messages.getString("menu.chooseOption"));

            // Get user input
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Using switch expression to handle user choices
            switch (choice) {
                case 1: // Add Book
                    System.out.print(messages.getString("prompt.title"));
                    String title = scanner.nextLine();
                    System.out.print(messages.getString("prompt.author"));
                    String author = scanner.nextLine();
                    System.out.print(messages.getString("prompt.isbn"));
                    String isbn = scanner.nextLine();
                    System.out.print(messages.getString("prompt.genre"));
                    String genreInput = scanner.nextLine().toUpperCase();
                    BookGenre genre = BookGenre.valueOf(genreInput); // Getting genre from input

                    library.addBook(new Book(title, author, isbn, genre)); // Adding book to library
                    System.out.println(messages.getString("message.bookAdded"));
                    break;

                case 2: // Remove Book
                    System.out.print(messages.getString("prompt.isbnRemove"));
                    String isbnToRemove = scanner.nextLine();
                    try {
                        library.removeBook(isbnToRemove); // Removing the book
                        System.out.println(messages.getString("message.bookRemoved"));
                    } catch (LibraryException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3: // Show Books
                    System.out.println(messages.getString("message.booksInLibrary"));
                    library.listBooks().forEach(System.out::println); // Using method reference for printing books
                    break;

                case 4: // Update Book
                    System.out.print(messages.getString("prompt.isbnUpdate"));
                    String isbnToUpdate = scanner.nextLine();
                    System.out.print(messages.getString("prompt.newTitle"));
                    String newTitle = scanner.nextLine();
                    System.out.print(messages.getString("prompt.newAuthor"));
                    String newAuthor = scanner.nextLine();
                    System.out.print(messages.getString("prompt.newGenre"));
                    String newGenreInput = scanner.nextLine().toUpperCase();
                    BookGenre newGenre = BookGenre.valueOf(newGenreInput); // Getting new genre from input

                    try {
                        library.updateBook(isbnToUpdate, newTitle, newAuthor, newGenre); // Updating the book
                        System.out.println(messages.getString("message.bookUpdated"));
                    } catch (LibraryException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5: // Submit Feedback
                    System.out.print(messages.getString("prompt.userName"));
                    String userName = scanner.nextLine();
                    System.out.print(messages.getString("prompt.feedbackMessage"));
                    String message = scanner.nextLine();
                    library.addFeedback(new Feedback(userName, message)); // Adding feedback
                    System.out.println(messages.getString("message.feedbackSubmitted"));
                    break;

                case 6: // View Feedback
                    System.out.println(messages.getString("message.feedbackReceived"));
                    library.listFeedback().forEach(System.out::println); // Using method reference for printing feedback
                    break;

                case 7: // Count Books
                    System.out.println(messages.getString("message.totalBooks") + library.countBooks()); // Counting books
                    break;

                case 8: // Change Language
                	   System.out.println("Choose language: 1.English 2.Hindi");
                       int langChoice = scanner.nextInt();
                       scanner.nextLine(); // Consume newline
                       String bundleName = (langChoice == 1) ? "Resources.Messages_eng" : "Resources.Messages_hi"; // Set bundle name based on choice
                       messages = ResourceBundle.getBundle(bundleName, currentLocale); // Reload messages
                       break;
                case 9: // Sort Books
                    System.out.println("Sort books by: 1. Title  2. Author  3. Genre 4. ISBN");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Comparator<Book> comparator = switch (sortChoice) {
                        case 1 -> Comparator.comparing(Book::getTitle);
                        case 2 -> Comparator.comparing(Book::getAuthor);
                        case 3 -> Comparator.comparing(Book::getGenre);
                        case 4 -> Comparator.comparing(Book::getIsbn);
                        default -> null;
                    };

                    if (comparator != null) {
                        List<Book> sortedBooks = library.sortBooks(comparator);
                        sortedBooks.forEach(System.out::println);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 10: // Exit
                    System.out.println(messages.getString("message.exiting"));
                    scanner.close(); // Closing the scanner
                    return;

                default:
                    System.out.println(messages.getString("message.invalidChoice")); // Handling invalid choices
            }
        }
    }
}