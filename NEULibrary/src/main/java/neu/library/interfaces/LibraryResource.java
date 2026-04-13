package neu.library.interfaces;

/**
 * Abstraction for any borrowable library resource.
 * High-level modules depend on this interface, not on concrete types.
 * Supports DIP and OCP — new resource types can be added without modifying existing code.
 */
public interface LibraryResource {
    String getTitle();
    String getResourceType();
    boolean isAvailable();
    void setAvailable(boolean available);
}
