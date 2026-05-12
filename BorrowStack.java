import java.util.Stack;

public class BorrowStack {
    private Stack<Book> stack = new Stack<>();

    public void push(Book b) { stack.push(b); }

    public void show() {
        if (stack.isEmpty()) {
            System.out.println("History is empty.");
        } else {
            // Display LIFO: Most recent first
            for (int i = stack.size() - 1; i >= 0; i--) {
                Book b = stack.get(i);
                System.out.println("[ISBN: " + b.getIsbn() + "] " + b.getTitle());
            }
        }
    }
}
