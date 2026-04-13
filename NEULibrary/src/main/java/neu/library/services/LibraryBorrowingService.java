package neu.library.services;

import neu.library.interfaces.Borrowable;
import neu.library.interfaces.BorrowingService;
import neu.library.interfaces.LibraryResource;

/**
 * Concrete implementation of BorrowingService.
 * SRP: handles only the borrowing/returning transaction logic.
 * DIP: Student depends on BorrowingService (abstraction), not this class directly.
 * OCP: New borrowing rules (e.g., due dates, fines) can be added via extension.
 */
public class LibraryBorrowingService implements BorrowingService {

    @Override
    public void borrowResource(String studentName, LibraryResource resource) {
        if (!(resource instanceof Borrowable)) {
            System.out.println("[Service] \"" + resource.getTitle() + "\" (" + resource.getResourceType()
                    + ") cannot be borrowed — in-library or digital access only.");
            return;
        }
        ((Borrowable) resource).borrow(studentName);
    }

    @Override
    public void returnResource(String studentName, LibraryResource resource) {
        if (!(resource instanceof Borrowable)) {
            System.out.println("[Service] \"" + resource.getTitle() + "\" is not a borrowable resource.");
            return;
        }
        ((Borrowable) resource).returnResource();
        System.out.println("[Service] Return confirmed for " + studentName + ".");
    }
}
