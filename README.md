# SMART-LIBRARY
Occ : 12 

Topic 2 : Smart Library  

Members : 

LIEVAASHINI A/P THANASEGARAN | 25006877/1 

SHAHITYA SHRI A/P SRI KRISHNAH | 25006586/1 

TIGITRA JAY C. SELVARAJA | 22113137/1 

KAMILIA AHLAM TAQI BINTI TAQI MOHAMED | 24001462/1 

WONG XIANG HONG | 25060693/1

## About the Project

Smart Library is a console-based library management system built in Java. It lets
the user manage books, borrowing, and fines through a simple menu. The project
applies core data structures from the course:

- **Binary Search Tree (BST)** — stores and searches books efficiently.
- **Stack** — keeps track of borrowing history (last borrowed, first viewed).

The program reads its starting data from two text files (`catalogue.txt` and
`fines.txt`) and lets the user add books, search by title, borrow and return
books, view borrowing history, view the total book count, and manage fine records.

## How to Run

1. Keep `app.jar`, `catalogue.txt`, and `fines.txt` in the **same folder**.
2. Open a terminal (Command Prompt or PowerShell) in that folder.
3. Run:

   ```
   java -jar app.jar
   ```

4. The SmartLibrary menu will appear. Type the option numbers to use it.

**Requirement:** Java must be installed (JDK 16 or any newer version).

## Menu Options

1. Add New Book
2. Search Book (BST)
3. Search Book By Title
4. Borrow Book (Stack)
5. View History
6. Return Book
7. View Total Book Count
8. Add Fine Record
9. View Fines
10. Exit

## Files Included

- `app.jar` — executable application (the program you run)
- `*.java` — source code (Book, BookBST, BorrowStack, Fine, FineManager, LibraryADT, Main, SmartLibrary)
- `catalogue.txt`, `fines.txt` — data files (required for the program to run)
- `DATA STRUCTURE REPORT_GROUP6.pdf` — project report

## Troubleshooting

### Problem 1: "Error: Unable to access jarfile app.jar"

**What it means:** The terminal cannot find the jar file.

**Cause A — Wrong folder:** Your terminal is open in a different folder than where
`app.jar` is saved.
- Fix: Move into the correct folder using `cd`, for example:
  `cd Desktop\SMART-LIBRARY`
- Then run `java -jar app.jar` again.

**Cause B — Wrong file name:** The file is saved as `app.jar.zip` instead of
`app.jar`.
- Fix: Run `dir` to list the files and check the exact name.
- If it shows `app.jar.zip`, right-click it, choose Rename, and remove the `.zip`
  so it reads exactly `app.jar`.

---

### Problem 2: "Could not find or load main class Main"

**What it means:** The jar opened but the program inside is broken.

**Cause:** The jar file was corrupted while being copied or downloaded.

**Fix:** Use the original `app.jar` from the submission folder. If it still fails,
rebuild it from the source code:
```
javac *.java
jar cfe app.jar Main *.class
```
(Run these in Command Prompt, inside the folder with the `.java` files.)

---

### Problem 3: Program opens but crashes when choosing Search Book or View Fines

**What it means:** The menu works, but an option that reads data fails.

**Cause:** The data files `catalogue.txt` and `fines.txt` are not in the same
folder as `app.jar`.

**Fix:** Place `app.jar`, `catalogue.txt`, and `fines.txt` together in one folder,
then run the program again.