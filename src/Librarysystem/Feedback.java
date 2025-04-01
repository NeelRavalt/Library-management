package Librarysystem;

public record Feedback(String userName, String message) {
    @SuppressWarnings("preview")
	@Override
    public String toString() {
        return STR."Feedback from \{userName}: \{message}"; // Java 23: String Templates
    }
}
