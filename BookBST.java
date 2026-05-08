public class BookBST {
    // methods must have : insert(), search(), delete()
    private Book root;

    public void insert(int isbn, String title, String author) {
        root = insertRec(root, new Book(isbn, title, author));
    }

    private Book insertRec(Book root, Book newBook) {
        if (root == null) {
            return newBook;
        }

        if (newBook.getIsbn() < root.getIsbn()) {
            root.setLeft(insertRec(root.getLeft(), newBook));
        } else if (newBook.getIsbn() > root.getIsbn()) {
            root.setRight(insertRec(root.getRight(), newBook));
        }

        return root;
    }
}
