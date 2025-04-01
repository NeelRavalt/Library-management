package Librarysystem;


// Book class demonstrating encapsulation
public class Book {
    private String title;
    private String author;
    private String isbn;
    private BookGenre genre; // Using enum for genre

    // Constructor
    public Book(String title, String author, String isbn, BookGenre genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
    }

    // Getters : Returns the value of their attribute
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public BookGenre getGenre() { return genre; }

    // Method overloading
//    public boolean displayInfo() {
//        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Genre: " + genre);
////		return false;
//	
//    }

    public void displayInfo(String additionalInfo) {
        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Genre: " + genre + ", Info: " + additionalInfo);
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + ", isbn='" + isbn + '\'' + ", genre=" + genre + '}';
    }
}
