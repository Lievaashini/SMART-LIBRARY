public class BookBST {
    // methods must have: insert(), search(), delete() 
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

    public Book search(int isbn) {
        return searchRec(root, isbn);
    }

    private Book searchRec(Book current, int isbn) {
        if (current == null || current.getIsbn() == isbn) {
            return current;
        }

        if (isbn < current.getIsbn()) {
            return searchRec(current.getLeft(), isbn);
        }
        return searchRec(current.getRight(), isbn);
    }

    public void delete(int isbn) {
        root = deleteRec(root, isbn);
    }

    private Book deleteRec(Book current, int isbn) {
        if (current == null) return null;

        if (isbn < current.getIsbn()) {
            current.setLeft(deleteRec(current.getLeft(), isbn));
        } else if (isbn > current.getIsbn()) {
            current.setRight(deleteRec(current.getRight(), isbn));
        } else {
            if (current.getLeft() == null) return current.getRight();
            if (current.getRight() == null) return current.getLeft();

            Book successor = findMin(current.getRight());
            current = new Book(successor.getIsbn(), successor.getTitle(), successor.getAuthor());
            current.setRight(deleteRec(current.getRight(), successor.getIsbn()));
        }
        return current;
    }

    private Book findMin(Book node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }
}
