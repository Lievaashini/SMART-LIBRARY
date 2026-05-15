   /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assisgnment_BorrowStack;

import java.util.Stack;

/**
 *
 * @author Asus
 */
public class BorrowStack {
    
    //Create stack to store Book objects
    private Stack <Book> stack = new Stack<>();
    
    //PUSH 
    public void push (Book b) {
        
    stack.push(b);
    
    System.out.println(b.title + " added to borrowing history");
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
            System.out.println("\n BORROWING HISTORY ");
            
            // Stack from top of stack
            for (int i = stack.size() - 1; i >= 0; i--){
                
                Book b = stack.get(i);
                
                System.out.println ("[ISBN: " + b.isbn +"] "+ b.title + " by " + b.author);
            }
        }
    }
}
