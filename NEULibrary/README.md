# NEU Library System — SOLID Refactoring

## Problem Statement

The NEU Library offers a variety of resources including **books, theses, capstones, internet access, journals, and newspapers**.

In the original design, the `Student` object contained methods like `borrowBook(title)` and `borrowJournal(title)` that **directly depended on specific, concrete resource types**. This violated the **Dependency Inversion Principle (DIP)** — the high-level `Student` module was tightly coupled to low-level concrete resource types.

The goal of this refactoring is to:
1. Apply **all five SOLID principles** to the NEU Library system.
2. Ensure flexibility for future resource types such as **audio books** and **e-journals** without modifying existing code.
3. Create a robust, testable system validated by a `TestProgram`.

---

## SOLID Principles Applied

| Principle | How it's applied |
|-----------|-----------------|
| **SRP** | Each class has one responsibility: `Student` handles identity + delegation; `LibraryBorrowingService` handles borrow/return logic; each resource model manages only its own data and access behavior. |
| **OCP** | New resource types (`EJournal`, `AudioBook`) were added by implementing existing interfaces — zero changes to `Student`, `LibraryBorrowingService`, or any existing model. |
| **LSP** | All resource types implement `LibraryResource` and can be used interchangeably wherever a `LibraryResource` is expected. Borrowable/ReadOnly/DigitalAccessible are checked before use, not assumed. |
| **ISP** | Three segregated interfaces: `Borrowable` (physical checkout), `ReadOnly` (in-library only), `DigitalAccessible` (online). No class is forced to implement methods it doesn't need — `Journal` implements only `ReadOnly`, not `Borrowable`. |
| **DIP** | `Student` depends on `BorrowingService` (interface) and `LibraryResource` (interface) — not on `Book`, `Journal`, or `LibraryBorrowingService` directly. Concrete service is injected via constructor. |

---

## UML Class Diagram

```
┌──────────────────────────────────────────────────────────────────────────┐
│                          «interface»                                     │
│                         LibraryResource                                  │
│  + getTitle(): String                                                    │
│  + getResourceType(): String                                             │
│  + isAvailable(): boolean                                                │
│  + setAvailable(boolean): void                                           │
└───────────────────────────────┬──────────────────────────────────────────┘
                                │ implements
        ┌───────────────────────┼─────────────────────────────────┐
        │                       │                                  │
┌───────▼──────┐    ┌───────────▼──────────┐         ┌───────────▼──────────┐
│  «interface» │    │     «interface»       │         │     «interface»      │
│  Borrowable  │    │      ReadOnly         │         │  DigitalAccessible   │
│─────────────-│    │──────────────────────│         │──────────────────────│
│ + borrow()   │    │ + accessInLibrary()  │         │ + accessOnline()     │
│ + return()   │    └──────────────────────┘         │ + getAccessUrl()     │
└──────────────┘                                     └──────────────────────┘
       │ implements                │ implements              │ implements
  ┌────┴────┐                 ┌────┴────┐             ┌──────┴──────┐
  │  Book   │  ┌──────────┐  │ Journal │             │  EJournal   │
  ├─────────┤  │  Thesis  │  ├─────────┤  ┌────────┐ ├─────────────┤
  │-title   │  ├──────────┤  │-title   │  │Newsp-  │ │-title       │
  │-author  │  │-title    │  │-volume  │  │aper    │ │-accessUrl   │
  │-avail.  │  │-author   │  └─────────┘  ├────────┤ └─────────────┘
  └─────────┘  │-year     │               │-title  │
               └──────────┘               │-date   │ ┌──────────────┐
                                          └────────┘ │  AudioBook   │
                                                     ├──────────────┤
                                                     │ implements   │
                                                     │ Borrowable + │
                                                     │ DigitalAcc.  │
                                                     └──────────────┘

┌─────────────────────────────────────────┐
│               Student                   │
│─────────────────────────────────────────│
│ - name: String                          │
│ - studentId: String                     │
│ - borrowingService: BorrowingService ◄──┼─── «interface» BorrowingService
│─────────────────────────────────────────│         + borrowResource()
│ + borrow(LibraryResource): void         │         + returnResource()
│ + returnResource(LibraryResource): void │              ▲
│ + readInLibrary(LibraryResource): void  │              │ implements
│ + accessOnline(LibraryResource): void   │   LibraryBorrowingService
└─────────────────────────────────────────┘
```

---

## Project Structure

```
NEULibrary/
└── src/
    └── main/
        └── java/
            └── neu/
                └── library/
                    ├── interfaces/
                    │   ├── LibraryResource.java       ← core abstraction
                    │   ├── Borrowable.java            ← ISP: physical borrow
                    │   ├── ReadOnly.java              ← ISP: in-library access
                    │   ├── DigitalAccessible.java     ← ISP: online access
                    │   └── BorrowingService.java      ← DIP: service abstraction
                    ├── models/
                    │   ├── Book.java
                    │   ├── Thesis.java
                    │   ├── Journal.java
                    │   ├── Newspaper.java
                    │   ├── EJournal.java              ← new: digital resource
                    │   ├── AudioBook.java             ← new: hybrid resource
                    │   └── Student.java               ← depends on abstractions
                    ├── services/
                    │   └── LibraryBorrowingService.java
                    └── test/
                        └── TestProgram.java           ← validates all behaviors
```

---

## How to Run

### Compile
```bash
mkdir -p out
find src -name "*.java" | xargs javac -d out
```

### Run Tests
```bash
java -cp out neu.library.test.TestProgram
```

### Expected Output (14 tests)
- Books and Theses: borrow, unavailability check, return
- Journals and Newspapers: in-library access only; borrow attempt rejected gracefully
- E-Journals: online access; borrow attempt rejected gracefully
- Audio Books: physical borrow AND online stream (hybrid resource)
- Edge cases: ReadOnly and DigitalAccessible on unsupported resource types

---

## Extensibility Example

To add a new resource type (e.g., `VideoLecture`) in the future:

```java
public class VideoLecture implements LibraryResource, DigitalAccessible {
    // implement only what's relevant — OCP + ISP
}
```

**Zero changes** to `Student`, `LibraryBorrowingService`, or any existing class.
