package neu.library.interfaces;

/**
 * Interface for resources accessible digitally (e.g., e-journals, audio books).
 * Added to support future digital resource types without modifying existing code — OCP.
 * Segregated from Borrowable/ReadOnly to follow ISP.
 */
public interface DigitalAccessible {
    void accessOnline(String userId);
    String getAccessUrl();
}
