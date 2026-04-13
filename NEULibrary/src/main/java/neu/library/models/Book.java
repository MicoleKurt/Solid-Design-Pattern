package neu.library.models;

import neu.library.interfaces.Borrowable;
import neu.library.interfaces.LibraryResource;

/**
 * Represents a physical book in the NEU Library.
 * Implements LibraryResource and Borrowable — books can be taken out.
 * SRP: only holds book data and borrowing behavior.
 */
public class Book implements LibraryResource, Borrowable {

    private final String title;
    private final String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getResourceType() {
        return "Book";
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public void borrow(String borrowerName) {
        if (!available) {
            System.out.println("[Book] \"" + title + "\" is currently not available.");
            return;
        }
        available = false;
        System.out.println("[Book] \"" + title + "\" borrowed by " + borrowerName + ".");
    }

    @Override
    public void returnResource() {
        available = true;
        System.out.println("[Book] \"" + title + "\" has been returned.");
    }
}
