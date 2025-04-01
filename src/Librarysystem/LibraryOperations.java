package Librarysystem;

import java.util.List;

// LibraryOperations interface with default and static methods
public interface LibraryOperations {
    void addBook(Book book);
    void removeBook(String isbn) throws LibraryException;
    List<Book> listBooks();

    // Default method
    default void printLibraryInfo() {
        System.out.println("Library has " + listBooks().size() + " books.");
    }

}
