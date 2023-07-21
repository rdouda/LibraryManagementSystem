package com.rdouda.librarymanagementsystem;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int isTaken = 0;

    public Book(int id, String title, String author, String isbn, int isTaken){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isTaken = isTaken;
    }

    public Book(String title, String author, String isbn){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Book(){

    }

    @Override
    public String toString(){
        return this.title + " " + this.author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(int isTaken) {
        this.isTaken = isTaken;
    }
}
