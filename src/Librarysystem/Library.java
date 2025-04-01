package Librarysystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library implements LibraryOperations {
    private List<Book> books = new ArrayList<>();
    private List<Feedback> feedbackList = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void removeBook(String isbn) throws LibraryException {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public void updateBook(String isbn, String newTitle, String newAuthor, BookGenre newGenre) throws LibraryException {
        Optional<Book> bookOpt = books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
        if (bookOpt.isPresent()) {
            removeBook(isbn);
            addBook(new Book(newTitle, newAuthor, isbn, newGenre));
        } else {
            throw new LibraryException("Book not found: " + isbn);
        }
    }

    @Override
    public List<Book> listBooks() {
        return books;
    }

    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }

    public List<Feedback> listFeedback() {
        return feedbackList;
    }

    public long countBooks() {
        return books.stream().count();
    }

    public long countFeedback() {
        return feedbackList.stream().count();
    }

    public List<Book> sortBooks(Comparator<Book> comparator) {
        return books.stream()
                    .sorted(comparator)
                    .toList(); // Java 16: Replaces `collect(Collectors.toList())`
    }

    // Java 11: Optimized File Handling
    public void saveBooksToFile(Path path) throws IOException {
        String bookData = books.stream()
                               .map(Book::toString)
                               .collect(Collectors.joining("\n"));
        Files.writeString(path, bookData, StandardOpenOption.CREATE);
    }

    public void loadBooksFromFile(Path path) throws IOException {
        String fileData = Files.readString(path);
        for (String line : fileData.split("\n")) {
            String[] parts = line.replace("Book{", "").replace("}", "").split(", ");
            String title = parts[0].split("=")[1];
            String author = parts[1].split("=")[1];
            String isbn = parts[2].split("=")[1];
            BookGenre genre = BookGenre.valueOf(parts[3].split("=")[1].toUpperCase());
            addBook(new Book(title, author, isbn, genre));
        }
    }
}
