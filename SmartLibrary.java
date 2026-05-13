package com.mycompany.smartlibraryproject; 

import java.util.Scanner;

public class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();

    @Override
    public void addBook(int i, String t, String a) {
        catalogue.insert(i, t, a);
    }

    // Return logic that only needs ISBN
    public void returnByIsbn(int isbn) {
        Book b = history.findAndRemove(isbn);
        
        System.out.println(""); 
        if (b != null) {
            catalogue.insert(b.getIsbn(), b.getTitle(), b.getAuthor());
            
            System.out.println("--- Return Confirmation ---");
            System.out.println("ISBN   : " + b.getIsbn());
            System.out.println("Title  : " + b.getTitle());
            System.out.println("Author : " + b.getAuthor());
            System.out.println("Result : Book returned successfully!");
            System.out.println("Status : Record restored to Catalogue.");
            System.out.println("---------------------------");
        } else {
            System.out.println("Error: This ISBN was not found in the Borrowed History.");
        }
    }

    @Override
    public void returnBook(int i, String t, String a) {
        addBook(i, t, a);
    } 
   
    @Override
    public void searchBook(int i) {
        Book b = catalogue.search(i);
        System.out.println(""); 
        if (b != null) {
            System.out.println("--- Book Details Found ---");
            System.out.println("ISBN   : " + b.getIsbn());
            System.out.println("Title  : " + b.getTitle());
            System.out.println("Author : " + b.getAuthor());
            System.out.println("--------------------------");
        } else {
            System.out.println("Result: Not Found.");
        }
    }

    @Override
    public void borrowBook(int i) {
        Book b = catalogue.search(i);
        System.out.println(""); 
        if (b != null) {
            history.push(b); 
            
            System.out.println("--- Borrowing Successful ---");
            System.out.println("Borrowed : " + b.getTitle());
            System.out.println("ISBN     : " + b.getIsbn());
            System.out.println("Author   : " + b.getAuthor());
            System.out.println("----------------------------");
            
            catalogue.remove(i); 
        } else {
            System.out.println("Result: Book not in the catalogue.");
        }
    }

    @Override
    public void viewLatestHistory() {
        System.out.println(""); 
        history.show();
    }

    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 6) {
            printMenu();
            System.out.print("Choice: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); 
                if (choice == 6) {
                    System.out.println("\nThank you for using SmartLibrary :)");
                } else {
                    handleChoice(choice, sc);
                }
            } else {
                System.out.println("\nInvalid input. Please enter a number (1-6).");
                sc.next();
            }
        }
        sc.close();
    }

    private void printMenu() {
        System.out.println("\n============================");
        System.out.println("    SMART LIBRARY MENU    ");
        System.out.println("============================");
        System.out.println("1. Add New Book");
        System.out.println("2. Search Book (BST)"); 
        System.out.println("3. Borrow Book (Stack)");
        System.out.println("4. View History"); 
        System.out.println("5. Return Book"); 
        System.out.println("6. Exit");
    }

    private void handleChoice(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                inputNewBook(sc);
                break;
            case 2:
                System.out.print("\nEnter ISBN to search: ");
                if (sc.hasNextInt()) {
                    searchBook(sc.nextInt());
                } else {
                    System.out.println("Invalid input.");
                    sc.next();
                }
                break;
            case 3:
                System.out.print("\nEnter ISBN to borrow: ");
                if (sc.hasNextInt()) {
                    borrowBook(sc.nextInt());
                } else {
                    System.out.println("Invalid input.");
                    sc.next();
                }
                break;
            case 4:
                viewLatestHistory();
                break;
            case 5:
                System.out.print("\nEnter ISBN to return: ");
                if (sc.hasNextInt()) {
                    returnByIsbn(sc.nextInt());
                } else {
                    System.out.println("Invalid input.");
                    sc.next();
                }
                break;
            default:
                System.out.println("\nInvalid option. Please try again.");
        }
    }

    private void inputNewBook(Scanner sc) {
        int i = 0;
        boolean valid = false;
        System.out.println(""); 
        while (!valid) {
            System.out.print("Enter ISBN: ");
            if (sc.hasNextInt()) {
                i = sc.nextInt(); 
                sc.nextLine();
                valid = true;
            } else {
                System.out.println("Invalid ISBN! Please enter a number."); 
                sc.next();
            }
        }
        System.out.print("Enter Title: ");
        String t = sc.nextLine();
        System.out.print("Enter Author: ");
        String a = sc.nextLine();
        
        addBook(i, t, a);
        System.out.println("\nResult: Book added successfully.");
    }
}
