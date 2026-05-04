import java.util.Scanner;

public class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST ();
    private BorrowStack borrowStack = new BorrowStack  ();

    public void runMenu (){
        Scanner sc = new Scanner (System.in);
        int choice = 0;

        while (choice != 6){
            printMenu();
            System.out.println ("Choice : ");
            if (sc.hasNextInt()){
                choice = sc.nextInt();
                handleChoice(choice);

            } else {
                System.out.println ("Invalid input. Please try again");
                sc.nextLine();
            }
        }
        sc.close();
    }

    public void printMenu(){
        System.out.println ("==== SMART LIBRARY ====");
        System.out.println ("1. Borrow Book");
        System.out.println ("2. Return Book");
        System.out.println ("3. Show Available Books");
        System.out.println ("4. Show Borrowed Books");
        System.out.println ("5. Search Book");
        System.out.println ("6. Exit");
    }

    public void handleChoice (int choice){
        Scanner sc = new Scanner (System.in);
        switch (choice) {
            case 1 :
                System.out.println ("Enter ISBN : ");
                int borrowIsbn = sc.nextInt();
                sc.nextLine();
                borrowBook (borrowIsbn);

            case 2 :
                System.out.println ("Enter ISBN : ");
                int returnIsbn = sc.nextInt();
                System.out.println ("Enter title : ");
                String returnTitle = sc.nextLine();
                System.out.println ("Enter author : ");
                String returnAuthor = sc.nextLine();
                returnBook (returnIsbn, returnTitle, returnAuthor);

            case 3 :

            case 4 :

            case 5 :

            case 6 :
                System.out.println ("Thank you for using SmartLibrary :D");
        }
        sc.close();
    }

    // Borrowing book method
    public void borrowBook(int isbn){
        Book b = catalogue.search (isbn); // search in catalogue for the book with specific isbn

        if (b==null){   // if book is not available in catalogue
            System.out.println ("Book not found");
            return;
        } else {
            borrowStack.push (b);   // add to borrowing stack
            catalogue.remove (b);   // remove from the catalogue
            System.out.println ("You have successfully borrowed book : " + isbn + ", " + b.title);
        }
    }

    public void returnBook (int isbn, String title, String author){
        catalogue.insert (isbn, title, author);
        borrowStack.pop(isbn);
        System.out.println ("Book returned successfully!");
    }
}
