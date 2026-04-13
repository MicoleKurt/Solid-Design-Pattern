package neu.library.interfaces;

/**
 * Abstraction for borrowing operations.
 * Student depends on this interface, not on a concrete service — DIP.
 */
public interface BorrowingService {
    void borrowResource(String studentName, LibraryResource resource);
    void returnResource(String studentName, LibraryResource resource);
}
