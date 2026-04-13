package neu.library.models;

import neu.library.interfaces.Borrowable;
import neu.library.interfaces.DigitalAccessible;
import neu.library.interfaces.LibraryResource;

/**
 * Represents an audio book in the NEU Library.
 * OCP: demonstrates extensibility — new type added with zero changes to existing classes.
 * Can be both borrowed physically AND accessed digitally — implements multiple interfaces.
 * SRP: manages only audio book data and its access behaviors.
 */
public class AudioBook implements LibraryResource, Borrowable, DigitalAccessible {

    private final String title;
    private final String narrator;
    private final String accessUrl;
    private boolean available;

    public AudioBook(String title, String narrator, String accessUrl) {
        this.title = title;
        this.narrator = narrator;
        this.accessUrl = accessUrl;
        this.available = true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getResourceType() {
        return "Audio Book";
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getNarrator() {
        return narrator;
    }

    @Override
    public void borrow(String borrowerName) {
        if (!available) {
            System.out.println("[Audio Book] \"" + title + "\" is currently not available.");
            return;
        }
        available = false;
        System.out.println("[Audio Book] \"" + title + "\" borrowed by " + borrowerName + ".");
    }

    @Override
    public void returnResource() {
        available = true;
        System.out.println("[Audio Book] \"" + title + "\" has been returned.");
    }

    @Override
    public void accessOnline(String userId) {
        System.out.println("[Audio Book] \"" + title + "\" streamed online by " + userId + ". URL: " + accessUrl);
    }

    @Override
    public String getAccessUrl() {
        return accessUrl;
    }
}
