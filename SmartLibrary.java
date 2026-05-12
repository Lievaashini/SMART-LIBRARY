import java.util.Scanner;

public class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST();
    private BorrowStack borrowStack = new BorrowStack();

    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            printMenu();
            System.out.print("Choice : "); 
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); 
                handleChoice(choice);
            } else {
                System.out.println("Invalid input. Please try again"); 
                sc.nextLine(); 
            }
        }
        sc.close();
    }

    public void printMenu() {
        System.out.println("\n==== SMART LIBRARY ===="); 
        System.out.println("1. Borrow Book"); 
        System.out.println("2. Return Book (Add to Catalogue)"); 
        System.out.println("3. Show Available Books");
        System.out.println("4. Show Borrowing History (Stack)"); 
        System.out.println("5. Search Book (BST)"); 
        System.out.println("6. Exit");
    }

    public void handleChoice(int choice) {
        Scanner sc = new Scanner(System.in);
        switch (choice) {
            case 1:
                System.out.print("Enter ISBN to borrow: "); 
                if (sc.hasNextInt()) {
                    borrowBook(sc.nextInt());
                }
                break; // Added break to stop fall-through

            case 2:
                System.out.print("Enter ISBN: "); 
                int rIsbn = sc.nextInt(); sc.nextLine();
                System.out.print("Enter Title: "); 
                String rTitle = sc.nextLine();
                System.out.print("Enter Author: "); 
                String rAuthor = sc.nextLine();
                returnBook(rIsbn, rTitle, rAuthor);
                break; 

            case 3:
                System.out.println("Catalogue (Available Books):");
                // This would typically call a display method in your BST
                break;

            case 4:
                viewLatestHistory(); // Displays Stack LIFO 
                break;

            case 5:
                System.out.print("Enter ISBN to search: "); 
                if (sc.hasNextInt()) {
                    searchBook(sc.nextInt()); // Uses your Record Finder search 
                }
                break;

            case 6:
                System.out.println("Thank you for using SmartLibrary :D");
                break;
                
            default:
                System.out.println("Invalid option."); 
        }
    }

    // Record Finder Search Implementation
    @Override
    public void searchBook(int isbn) {
        Book b = catalogue.search(isbn); // Your recursive BST search
        if (b != null) {
            System.out.println("Found: " + b.getTitle() + " by " + b.getAuthor()); 
        } else {
            System.out.println("Book not found."); 
        }
    }

    @Override
    public void borrowBook(int isbn) {
        Book b = catalogue.search(isbn); // Search before borrowing 

        if (b == null) {
            System.out.println("Book not in catalogue."); 
        } else {
            borrowStack.push(b); // Move record to history stack 
            catalogue.remove(isbn); // Remove from catalogue logic 
            System.out.println("Successfully borrowed: " + b.getTitle()); 
        }
    }

    // History Logic
    @Override
    public void viewLatestHistory() {
        borrowStack.show(); // Requirement: LIFO order 
    }

    @Override
    public void addBook(int isbn, String title, String author) {
        catalogue.insert(isbn, title, author); 
    }

    public void returnBook(int isbn, String title, String author) {
        addBook(isbn, title, author);
        System.out.println("Book returned and added back to catalogue successfully!");
    }
}
