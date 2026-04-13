package neu.library.models;

import neu.library.interfaces.BorrowingService;
import neu.library.interfaces.DigitalAccessible;
import neu.library.interfaces.LibraryResource;
import neu.library.interfaces.ReadOnly;

/**
 * Represents a NEU Library student.
 *
 * SOLID adherence:
 *  - SRP: Student only holds student identity and delegates all resource operations.
 *  - DIP: Depends on BorrowingService and LibraryResource abstractions, NOT on
 *         concrete types like Book or Journal. No borrowBook(title) or borrowJournal(title).
 *  - OCP: Adding new resource types (AudioBook, EJournal) requires zero changes here.
 *  - LSP: Works with any LibraryResource subtype interchangeably.
 *  - ISP: Only calls interface methods relevant to what each resource supports.
 */
public class Student {

    private final String name;
    private final String studentId;
    private final BorrowingService borrowingService;

    public Student(String name, String studentId, BorrowingService borrowingService) {
        this.name = name;
        this.studentId = studentId;
        this.borrowingService = borrowingService;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    /**
     * Borrow any borrowable library resource.
     * Student does not know or care about the concrete resource type.
     */
    public void borrow(LibraryResource resource) {
        System.out.println("\n[Student: " + name + "] Requesting to borrow: " + resource.getTitle());
        borrowingService.borrowResource(name, resource);
    }

    /**
     * Return a previously borrowed resource.
     */
    public void returnResource(LibraryResource resource) {
        System.out.println("\n[Student: " + name + "] Returning: " + resource.getTitle());
        borrowingService.returnResource(name, resource);
    }

    /**
     * Read a read-only resource (journals, newspapers) in-library.
     */
    public void readInLibrary(LibraryResource resource) {
        System.out.println("\n[Student: " + name + "] Reading in-library: " + resource.getTitle());
        if (resource instanceof ReadOnly) {
            ((ReadOnly) resource).accessInLibrary(name);
        } else {
            System.out.println("[Student] \"" + resource.getTitle() + "\" does not support in-library read mode.");
        }
    }

    /**
     * Access a digital resource online (e-journals, audio books, future e-resources).
     */
    public void accessOnline(LibraryResource resource) {
        System.out.println("\n[Student: " + name + "] Accessing online: " + resource.getTitle());
        if (resource instanceof DigitalAccessible) {
            ((DigitalAccessible) resource).accessOnline(studentId);
        } else {
            System.out.println("[Student] \"" + resource.getTitle() + "\" is not available for online access.");
        }
    }
}
