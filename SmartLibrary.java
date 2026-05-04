public class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST ();
    private BorrowStack borrowStack = new BorrowStack  ();

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
}
