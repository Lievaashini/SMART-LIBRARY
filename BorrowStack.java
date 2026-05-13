package com.mycompany.smartlibraryproject; 

import java.util.Stack;

public class BorrowStack {
    private Stack<Book> stack = new Stack<>();

    public void push(Book b) {
        if (b != null) stack.push(b);
    }

    // NEW: Find a book by ISBN to facilitate returning it
    public Book findAndRemove(int isbn) {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i).getIsbn() == isbn) {
                return stack.remove(i); // Removes from history and returns the book object
            }
        }
        return null;
    }

    public void show() {
        if (stack.isEmpty()) {
            System.out.println("History is empty.");
        } else {
            System.out.println("--- Borrowing History (LIFO) ---");
            for (int i = stack.size() - 1; i >= 0; i--) {
                Book b = stack.get(i);
                System.out.println("[ISBN: " + b.getIsbn() + "] " + b.getTitle());
            }
        }
    }
}
