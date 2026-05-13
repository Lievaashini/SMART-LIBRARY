
package com.mycompany.smartlibraryproject; 

package smartlibraryproject;

public class BookBST {
    private Book root;

    public void insert(int i, String t, String a) {
        root = insertRec(root, new Book(i, t, a));
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

    // TASK 3: Recursive Search function (O(log n) efficiency) 
    public Book search(int i) {
        return searchRec(root, i); 
    }

    private Book searchRec(Book current, int i) {
        // Base Case: Book not found or ISBN matches 
        if (current == null || current.getIsbn() == i) {
            return current;
        }

        // Recursive Step: Logarithmic traversal 
        if (i < current.getIsbn()) {
            return searchRec(current.getLeft(), i);
        }
        return searchRec(current.getRight(), i);
    }

    // TASK 5: Admin Logic - Handle removing from catalogue 
    public void remove(int i) {
        root = deleteRec(root, i);
    }

    private Book deleteRec(Book root, int i) {
        if (root == null) return null;
        if (i < root.getIsbn()) root.setLeft(deleteRec(root.getLeft(), i));
        else if (i > root.getIsbn()) root.setRight(deleteRec(root.getRight(), i));
        else {
            if (root.getLeft() == null) return root.getRight();
            if (root.getRight() == null) return root.getLeft();
            Book successor = findMin(root.getRight());
            root = new Book(successor.getIsbn(), successor.getTitle(), successor.getAuthor());
            root.setRight(deleteRec(root.getRight(), successor.getIsbn()));
        }
        return root;
    }

    private Book findMin(Book node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }
}
