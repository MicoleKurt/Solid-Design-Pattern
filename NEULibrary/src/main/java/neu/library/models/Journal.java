package neu.library.models;

import neu.library.interfaces.LibraryResource;
import neu.library.interfaces.ReadOnly;

/**
 * Represents a physical journal in the NEU Library.
 * Journals are for in-library use only — implements ReadOnly, NOT Borrowable.
 * ISP: does not force unnecessary borrowing methods on this resource type.
 * SRP: only manages journal data and in-library access behavior.
 */
public class Journal implements LibraryResource, ReadOnly {

    private final String title;
    private final String volume;
    private boolean available;

    public Journal(String title, String volume) {
        this.title = title;
        this.volume = volume;
        this.available = true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getResourceType() {
        return "Journal";
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getVolume() {
        return volume;
    }

    @Override
    public void accessInLibrary(String readerName) {
        System.out.println("[Journal] \"" + title + "\" (Vol. " + volume + ") accessed in-library by " + readerName + ".");
    }
}
