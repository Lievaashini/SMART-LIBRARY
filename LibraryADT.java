public interface LibraryADT {
    void addBook(int i, String t, String a);
    void searchBook(int i);
    void searchBookByTitle(String title);
    void borrowBook(int i);
    void returnBook(int i, String t, String a);
    void viewLatestHistory();
}
