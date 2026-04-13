## Problem 
NEULibrary/
├── README.md               ← problem statement + UML class diagram
└── src/main/java/neu/library/
    ├── interfaces/
    │   ├── LibraryResource.java       ← core abstraction (DIP)
    │   ├── Borrowable.java            ← physical borrow (ISP)
    │   ├── ReadOnly.java              ← in-library access (ISP)
    │   ├── DigitalAccessible.java     ← online access (ISP)
    │   └── BorrowingService.java      ← service abstraction (DIP)
    ├── models/
    │   ├── Book.java, Thesis.java     ← Borrowable resources
    │   ├── Journal.java, Newspaper.java ← ReadOnly resources
    │   ├── EJournal.java              ← DigitalAccessible (new type, OCP)
    │   ├── AudioBook.java             ← Borrowable + Digital (hybrid, OCP)
    │   └── Student.java               ← depends only on interfaces (DIP)
    ├── services/
    │   └── LibraryBorrowingService.java
    └── test/
        └── TestProgram.java           ← 14 test cases, all passing


## Class Diagram
![image](https://github.com/JerryEsperanza/factoryPattern/assets/142370600/0506f134-a5f6-4d98-a817-cd6f7a8466c7)
