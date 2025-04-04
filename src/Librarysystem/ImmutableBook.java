package Librarysystem;

// Custom Immutable Book class
public final class ImmutableBook {
    private final String title;
    private final String author;
    private final String isbn;

    public ImmutableBook(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
}
