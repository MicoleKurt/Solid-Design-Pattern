package neu.library.interfaces;

/**
 * Interface for resources that are read-only / in-library use only (e.g., newspapers, journals).
 * Segregated from Borrowable to follow ISP — clients only depend on what they need.
 */
public interface ReadOnly {
    void accessInLibrary(String readerName);
}
