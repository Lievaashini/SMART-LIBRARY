import java.util.Stack;

public class BorrowStack {
    
    //Create stack to store Book objects
    private Stack <Book> stack = new Stack<>();
    
    //PUSH 
    public void push (Book b) {
        
    stack.push(b);
    
    System.out.println(b.getTitle() + " added to borrowing history");
    }
    
    //POP
    public Book pop(){
        
        if(stack.isEmpty()){
            
            System.out.println("History is empty. ");
            return null;
        }
        return stack.pop();
    }
    
    //PEEK
    public Book peek (){
    
        if(stack.isEmpty()){
            System.out.println("History is empty.");
            return null;
        }
        return stack.peek();
    }
    
    //CHECK EMPTY
    public boolean isEmpty(){
        return stack.isEmpty();
    }
        
    //SHOW
    public void  show(){
        
        //Check if stack is empty
        if(stack.isEmpty()){
            System.out.println("History is empty.");
        }
    
        else{
            System.out.println("\n--- Borrowing History ---");
            
            // Stack from top of stack
            for (int i = stack.size() - 1; i >= 0; i--){
                
                Book b = stack.get(i);
                
                System.out.println ("[ISBN: " + b.getIsbn() +"] "+ b.getTitle() + " by " + b.getAuthor());
            }
            System.out.println("-------------------------");   
        }
    }

   // Find a book by ISBN to support single-input return
    public Book findAndRemove(int isbn) {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i).getIsbn() == isbn) {
                return stack.remove(i); // Removes from stack and passes the details back
            }
        }
        return null; // Book wasn't found in history
    }

     // EXTRA FEATURE: Use built-in stack size method for the total count
    public int getBorrowedCount() {
        return stack.size();
    }   
    
}
