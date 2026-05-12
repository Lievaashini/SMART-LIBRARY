public class BookBST {
    private Book root;

    public void insert(int isbn, String title, String author) {
        root = insertRec(root, new Book(isbn, title, author));
    }

    private Book insertRec(Book root, Book newBook) {
        if (root == null) return newBook;
        if (newBook.getIsbn() < root.getIsbn()) {
            root.setLeft(insertRec(root.getLeft(), newBook));
        } else if (newBook.getIsbn() > root.getIsbn()) {
            root.setRight(insertRec(root.getRight(), newBook));
        }
        return root;
    }

    // Recursive Search (O(log n) efficiency)
    public Book search(int isbn) {
        return searchRec(root, isbn);
    }

    private Book searchRec(Book current, int isbn) {
        if (current == null || current.getIsbn() == isbn) return current;
        return (isbn < current.getIsbn()) ? 
                searchRec(current.getLeft(), isbn) : searchRec(current.getRight(), isbn);
    }

    // Delete logic for borrowing process
    public void remove(int isbn) {
        root = deleteRec(root, isbn);
    }

    private Book deleteRec(Book root, int isbn) {
        if (root == null) return null;
        if (isbn < root.getIsbn()) root.setLeft(deleteRec(root.getLeft(), isbn));
        else if (isbn > root.getIsbn()) root.setRight(deleteRec(root.getRight(), isbn));
        else {
            if (root.getLeft() == null) return root.getRight();
            if (root.getRight() == null) return root.getLeft();
            Book temp = findMin(root.getRight());
            root = new Book(temp.getIsbn(), temp.getTitle(), temp.getAuthor());
            root.setRight(deleteRec(root.getRight(), temp.getIsbn()));
        }
        return root;
    }

    private Book findMin(Book root) {
        while (root.getLeft() != null) root = root.getLeft();
        return root;
    }
}
