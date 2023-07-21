package com.rdouda.librarymanagementsystem;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {

    @Test
    void addBooks() {
        DatabaseManager.database();
        try{
            DatabaseManager.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", "12094443"));
            DatabaseManager.addBook(new Book("1984", "George Orwell", "23567888"));
            DatabaseManager.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "67896777"));
            DatabaseManager.addBook(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "456455544"));
            DatabaseManager.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "345555532"));
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            fail("An unexpected SQLException occured");
        } finally {
            DatabaseManager.closeConnection();
        }
    }

    @Test
    void removeAllBooks(){
        DatabaseManager.database();
        try {
            DatabaseManager.removeAllBooks();
            int booksCount = DatabaseManager.getBooks().size();
            assertEquals(0 , booksCount);
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            fail("An unexpected SQLException occured");
        } finally {
            DatabaseManager.closeConnection();
        }
    }

    @Test
    void getBook() {
        DatabaseManager.database();
        String isbn = "67896777";
        try {
            Book book = DatabaseManager.getBook(isbn);
            if (book == null)
                System.out.println("Book does not exist.");
            else
                System.out.println(book);
            assert true;
        } catch (SQLException sqlException) {
            System.out.println("An unexpected SQLException occured.");
            fail();
        } finally {
            DatabaseManager.closeConnection();
        }
    }
}