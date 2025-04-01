package Librarysystem;

import java.time.LocalDateTime;

public class Transaction {
    private User user;
    private Book book;
    private LocalDateTime dateTime; // Java 8+

    public Transaction(User user, Book book) {
        this.user = user;
        this.book = book;
        this.dateTime = LocalDateTime.now(); // Java 8: Better than Date
    }

    @SuppressWarnings("preview")
	@Override
    public String toString() {
        return STR."Transaction: User \{user.getName()} borrowed \{book.getTitle()} on \{dateTime}";
    }
}
