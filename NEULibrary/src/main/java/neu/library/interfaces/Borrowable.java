package neu.library.interfaces;

/**
 * Interface for resources that can be physically borrowed (e.g., books, theses).
 * Kept separate from other resource behaviors to follow ISP.
 */
public interface Borrowable {
    void borrow(String borrowerName);
    void returnResource();
}
