package neu.library.models;

import neu.library.interfaces.Borrowable;
import neu.library.interfaces.LibraryResource;

/**
 * Represents a thesis/capstone in the NEU Library.
 * Implements LibraryResource and Borrowable.
 * SRP: only manages thesis data and borrowing behavior.
 */
public class Thesis implements LibraryResource, Borrowable {

    private final String title;
    private final String author;
    private final int year;
    private boolean available;

    public Thesis(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getResourceType() {
        return "Thesis";
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

    public int getYear() {
        return year;
    }

    @Override
    public void borrow(String borrowerName) {
        if (!available) {
            System.out.println("[Thesis] \"" + title + "\" is currently not available.");
            return;
        }
        available = false;
        System.out.println("[Thesis] \"" + title + "\" borrowed by " + borrowerName + ".");
    }

    @Override
    public void returnResource() {
        available = true;
        System.out.println("[Thesis] \"" + title + "\" has been returned.");
    }
}
