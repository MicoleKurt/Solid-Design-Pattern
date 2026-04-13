package neu.library.test;

import neu.library.interfaces.BorrowingService;
import neu.library.models.*;
import neu.library.services.LibraryBorrowingService;

/**
 * TestProgram validates the refactored NEU Library system.
 * Tests all resource types and verifies SOLID principle adherence at runtime.
 */
public class TestProgram {

    public static void main(String[] args) {

        System.out.println("=".repeat(60));
        System.out.println("   NEU Library System — SOLID Refactoring Test");
        System.out.println("=".repeat(60));

        // DIP: Student depends on BorrowingService interface, not the concrete class.
        BorrowingService service = new LibraryBorrowingService();
        Student alice = new Student("Alice Reyes", "STU-2024-001", service);
        Student bob   = new Student("Bob Santos",  "STU-2024-002", service);

        // ── Resource instances ──────────────────────────────────────────
        Book book           = new Book("Clean Code", "Robert C. Martin");
        Thesis thesis       = new Thesis("AI in Education", "Maria Cruz", 2023);
        Journal journal     = new Journal("IEEE Software", "Vol. 41");
        Newspaper newspaper = new Newspaper("Manila Bulletin", "April 13, 2026");
        EJournal ejournal   = new EJournal("Nature Digital Medicine", "https://nature.com/ndm");
        AudioBook audioBook = new AudioBook("Atomic Habits", "Joe Rogan", "https://neu.edu.ph/audio/atomic-habits");

        // ── TEST 1: Borrow a Book ───────────────────────────────────────
        separator("TEST 1: Borrowing a Book");
        alice.borrow(book);

        // ── TEST 2: Attempt to borrow the same book (unavailable) ───────
        separator("TEST 2: Borrow same Book while unavailable");
        bob.borrow(book);

        // ── TEST 3: Return the Book ─────────────────────────────────────
        separator("TEST 3: Returning the Book");
        alice.returnResource(book);

        // ── TEST 4: Bob borrows after return ────────────────────────────
        separator("TEST 4: Bob borrows after return");
        bob.borrow(book);
        bob.returnResource(book);

        // ── TEST 5: Borrow a Thesis ─────────────────────────────────────
        separator("TEST 5: Borrowing a Thesis");
        alice.borrow(thesis);
        alice.returnResource(thesis);

        // ── TEST 6: In-library access — Journal (ReadOnly, not Borrowable)
        separator("TEST 6: In-library access — Journal");
        alice.readInLibrary(journal);

        // ── TEST 7: Attempt to borrow a Journal (should be rejected) ────
        separator("TEST 7: Attempt to borrow a Journal (should fail gracefully)");
        alice.borrow(journal);

        // ── TEST 8: In-library access — Newspaper ───────────────────────
        separator("TEST 8: In-library access — Newspaper");
        bob.readInLibrary(newspaper);

        // ── TEST 9: Online access — E-Journal ───────────────────────────
        separator("TEST 9: Online access — E-Journal");
        alice.accessOnline(ejournal);

        // ── TEST 10: Attempt to borrow an E-Journal (not Borrowable) ────
        separator("TEST 10: Attempt to borrow an E-Journal (should fail gracefully)");
        alice.borrow(ejournal);

        // ── TEST 11: Audio Book — borrow physically ──────────────────────
        separator("TEST 11: Audio Book — physical borrow");
        alice.borrow(audioBook);
        alice.returnResource(audioBook);

        // ── TEST 12: Audio Book — online stream ──────────────────────────
        separator("TEST 12: Audio Book — online stream");
        bob.accessOnline(audioBook);

        // ── TEST 13: Read Journal in-library (ReadOnly, should NOT support borrow) ─
        separator("TEST 13: Try readInLibrary on a Book (should not support it)");
        alice.readInLibrary(book);  // Book is Borrowable, not ReadOnly

        // ── TEST 14: Try accessOnline on a physical-only Book ───────────
        separator("TEST 14: Try accessOnline on a Book (should fail gracefully)");
        alice.accessOnline(book);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("   All tests complete. Review output above.");
        System.out.println("=".repeat(60));
    }

    private static void separator(String label) {
        System.out.println("\n── " + label + " ──");
    }
}
