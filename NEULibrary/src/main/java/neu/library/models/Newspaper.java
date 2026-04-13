package neu.library.models;

import neu.library.interfaces.LibraryResource;
import neu.library.interfaces.ReadOnly;

/**
 * Represents a newspaper in the NEU Library.
 * For in-library reading only — implements ReadOnly, NOT Borrowable.
 * ISP: only carries the interface methods relevant to newspapers.
 * SRP: manages only newspaper data and in-library access.
 */
public class Newspaper implements LibraryResource, ReadOnly {

    private final String title;
    private final String date;
    private boolean available;

    public Newspaper(String title, String date) {
        this.title = title;
        this.date = date;
        this.available = true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getResourceType() {
        return "Newspaper";
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDate() {
        return date;
    }

    @Override
    public void accessInLibrary(String readerName) {
        System.out.println("[Newspaper] \"" + title + "\" (" + date + ") read in-library by " + readerName + ".");
    }
}
