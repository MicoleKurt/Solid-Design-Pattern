package neu.library.models;

import neu.library.interfaces.DigitalAccessible;
import neu.library.interfaces.LibraryResource;

/**
 * Represents a digital/online journal (e-journal) in the NEU Library.
 * OCP: new resource type added without modifying existing classes.
 * ISP: only implements DigitalAccessible — not Borrowable or ReadOnly.
 * SRP: manages only e-journal data and online access behavior.
 */
public class EJournal implements LibraryResource, DigitalAccessible {

    private final String title;
    private final String accessUrl;
    private boolean available;

    public EJournal(String title, String accessUrl) {
        this.title = title;
        this.accessUrl = accessUrl;
        this.available = true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getResourceType() {
        return "E-Journal";
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void accessOnline(String userId) {
        System.out.println("[E-Journal] \"" + title + "\" accessed online by " + userId + ". URL: " + accessUrl);
    }

    @Override
    public String getAccessUrl() {
        return accessUrl;
    }
}
