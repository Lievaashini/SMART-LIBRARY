/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libraryadtproject;

/**
 *
 * @author LENOVO
 */
public class Book {
    private int isbn;
    private String title;
    private String author;
    private Book left;
    private Book right;
    
    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
    
    public int getIsbn() {
        return isbn;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public Book getLeft() {
        return left;
    }
    
    public Book getRight() {
        return right;
    }
    
    public void setLeft(Book left) {
        this.left = left;
    }
    
    public void setRight (Book right) {
        this.right = right;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> b75117c (Book)
