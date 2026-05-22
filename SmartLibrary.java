import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST ();
    private BorrowStack borrowStack = new BorrowStack ();
    private FineManager fineManager = new FineManager();

    public void runMenu (){
        loadCatalogueFromFile();
        fineManager.loadFinesFromFile();

        Scanner sc = new Scanner (System.in);
        int choice = 0;

        while (choice != 9){
            printMenu();
            System.out.println ("Choice : ");
            if (sc.hasNextInt()){
                choice = sc.nextInt();
                sc.nextLine();
                handleChoice(choice, sc);

            } else {
                System.out.println ("Invalid input. Please try again");
                sc.nextLine();
            }
        }
        sc.close();
    }

    public void printMenu(){
        System.out.println ("\n--- SmartLibrary Menu ---");
        System.out.println ("1. Add New Book");
        System.out.println ("2. Search Book (BST)");
        System.out.println ("3. Borrow Book (Stack)");
        System.out.println ("4. View History");
        System.out.println ("5. Return Book");
        System.out.println ("6. View Total Book Count");
        System.out.println ("7. Add Fine Record");
        System.out.println ("8. View Fines");
        System.out.println ("9. Exit"); 
    }

    public void handleChoice (int choice, Scanner sc){
        switch (choice) {

            case 1 :    // Add book
                int newIsbn = 0;
                System.out.println ("Enter ISBN : ");
                if (sc.hasNextInt()) {
                    newIsbn = sc.nextInt();
                } else {
                    System.out.println("Invalid ISBN! Please enter numbers only.");
                }
                sc.nextLine();
                System.out.println ("Enter title : ");
                String newTitle = sc.nextLine();
                System.out.println ("Enter author : ");
                String newAuthor = sc.nextLine();
                addBook(newIsbn, newTitle, newAuthor);
                break;

            case 2 :    // Search book
                int searchIsbn = 0;
                System.out.println ("Enter ISBN : ");
                if (sc.hasNextInt()) {
                    searchIsbn = sc.nextInt();
                } else {
                    System.out.println("Invalid ISBN! Please enter numbers only.");
                    sc.next();
                }
                searchBook(searchIsbn);
                break;

            case 3 :    // Borrow book     
                System.out.println ("Enter ISBN : ");
                if (sc.hasNextInt()) {
                     int borrowIsbn = sc.nextInt();
                     sc.nextLine();
                     borrowBook (borrowIsbn);
                } else {
                    System.out.println("Invalid ISBN! Please enter numbers only.");
                    sc.nextLine(); 
                }
                break;

            case 4 :    // View borrowed history
                viewLatestHistory();
                break;

            case 5 :    // Return book 
                System.out.println("\nEnter ISBN to return : ");
                if (sc.hasNextInt()) {
                    int returnIsbn = sc.nextInt();
                    sc.nextLine();
                    // Looks for the book in the borrowing history stack
                    Book b = borrowStack.findAndRemove(returnIsbn);

                    if (b != null) {
                        // Restores it back to the catalogue BST structure
                        catalogue.insert(b.getIsbn(), b.getTitle(), b.getAuthor());
                        System.out.println("\n--- Return Confirmation ---");
                        System.out.println("ISBN   : " + b.getIsbn());
                        System.out.println("Title  : " + b.getTitle());
                        System.out.println("Author : " + b.getAuthor());
                        System.out.println("Result : Book returned successfully!");
                        System.out.println("Status : Record restored to Catalogue.");
                    } else {
                        System.out.println("Error: This ISBN was not found in the Borrowed History.");
                    }
                    System.out.println("--------------------------");
                } else {
                    System.out.println("Invalid ISBN! Please enter numbers only.");
                    sc.nextLine();
                }
                break;

             case 6 :    // Extra feature: View Total Book Count
                System.out.println(""); 
                System.out.println("--- Library Statistics ---");
                System.out.println("Available Books in Catalogue   : " + catalogue.getBookCount());
                System.out.println("Total Books Currently Borrowed : " + borrowStack.getBorrowedCount());
                System.out.println("--------------------------");
                break;

             case 7 :    // Extra feature: Add fine record
                System.out.println("\n--- Add Fine Record ---");
                System.out.print("Enter ISBN: ");
                int isbn = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Student Name: ");
                String studentName = sc.nextLine();
                System.out.print("Enter Overdue Days: ");
                int overdueDays = sc.nextInt();
                sc.nextLine();
                fineManager.addFine(isbn, studentName, overdueDays);
                System.out.println("Fine record added successfully!");
                break;

             case 8 :    // Extra feature: View fine records
                fineManager.displayFines();

             case 9 :    // Exit program
                fineManager.saveFinesToFile();
                System.out.println ("Thank you for using SmartLibrary :D");
                break;
                
            default :
                System.out.println ("Invalid input. Please try again");
        }
    }

// Borrowing book method
    public void borrowBook(int isbn){
        Book b = catalogue.search (isbn); // search in catalogue for the book with specific isbn
        
        System.out.println("\n--- Borrow Transaction ---");
        if (b==null){   // if book is not available in catalogue
            System.out.println ("Result: Book not in catalogue. ");
            return;
        } else {
            borrowStack.push (b);   // add to borrowing stack
            catalogue.remove (b.getIsbn());   // remove from the catalogue
            System.out.println ("You have successfully borrowed book : " + isbn + ", " + b.getTitle());
        }
        System.out.println("--------------------------");
    }

        public void returnBook (int isbn, String title, String author){
            catalogue.insert (isbn, title, author);
            borrowStack.pop();
            System.out.println("\n--- Return Transaction ---");
            System.out.println("ISBN   : " + isbn);
            System.out.println("Title  : " + title);
            System.out.println ("Book returned successfully!");
            System.out.println("--------------------------");
        }

    @Override
    public void addBook(int isbn, String title, String author) {
        catalogue.insert(isbn, title, author);
        saveBookToFile(isbn, title, author);
        System.out.println("\n--- Book Registration ---");
        System.out.println ("Result: Book added successfully!");
        System.out.println("-------------------------");
    }

    @Override
    public void searchBook(int isbn) {
        Book b = catalogue.search(isbn);
        System.out.println("\n--- Search Results ---");
        if (b == null) {
            System.out.println ("Result: Book not found.");
        } else {
            System.out.println ("Found: [ISBN: " + b.getIsbn() + "] " + b.getTitle() + " by " + b.getAuthor());
        }
        System.out.println("----------------------");
    }

    @Override
    public void viewLatestHistory() {
        borrowStack.show();
    }

    private void saveBookToFile(int isbn, String title, String author) {
        try (FileWriter fw = new FileWriter("catalogue.txt", true);
            PrintWriter pw = new PrintWriter(fw)) {
        
            pw.println(isbn + "," + title + "," + author);
            
        } catch (IOException e) {
            System.out.println("Error saving book to file: " + e.getMessage());
        }
    }

    public void loadCatalogueFromFile() {
        File file = new File("catalogue.txt");
        
        // If the file doesn't exist yet, skip loading
        if (!file.exists()) {
            return; 
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                
                // Skip empty lines if there are any
                if (line.trim().isEmpty()) continue; 
                
                // Split the line by the semicolon delimiter
                String[] data = line.split(",");
                
                if (data.length == 3) {
                    String cleanIsbnStr = data[0].replaceAll("[^0-9]", "").trim();
                    int isbn = Integer.parseInt(cleanIsbnStr);
                    String title = data[1].trim();
                    String author = data[2].trim();
                    catalogue.insert(isbn, title, author);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found.");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data from file: " + e.getMessage());
        }
    }
} 
