package com.rdouda.core;

import com.rdouda.core.database.BookManager;
import com.rdouda.core.database.DatabaseManager;
import com.rdouda.core.database.PatronManager;
import com.rdouda.core.library.Book;
import com.rdouda.core.library.Patron;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {

    @Test
    void addBooks() {
        DatabaseManager.database();
        try{
            BookManager.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", "12094443"));
            BookManager.addBook(new Book("1984", "George Orwell", "23567888"));
            BookManager.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "67896777"));
            BookManager.addBook(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "456455544"));
            BookManager.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "345555532"));
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            fail("An unexpected SQLException occured");
        }
    }
    @Test
    void getBook() {
        DatabaseManager.database();
        String isbn = "67896777";
        Book book = new Book();
        book.setIsbn("345555532");
        try {
            Book bookFound = BookManager.getBook(book);
            if (bookFound == null)
                System.out.println("Book does not exist.");
            else
                System.out.println(bookFound);
            assert true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            System.out.println("An unexpected SQLException occured.");
            fail();
        }
    }
    @Test
    void removeAllBooks(){
        DatabaseManager.database();
        try {
            BookManager.removeAllBooks();
            int booksCount = BookManager.getBooks().size();
            assertEquals(0 , booksCount);
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            fail("An unexpected SQLException occured");
        }
    }
    @Test
    void closeDatabaseConnection(){
        DatabaseManager.closeConnection();
    }

    @Test
    void addPatron(){
        DatabaseManager.database();
        try{
            PatronManager.addPatron(new Patron("Rayen Raddaoui", "55607817", "98734583"));
            PatronManager.addPatron(new Patron("Zakariya Raddaoui", "90603817", "98734581"));
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            fail("An unexpected SQLException occured");
        }
    }

    @Test
    void getAllPatrons(){
        DatabaseManager.database();
        try{
            ArrayList<Patron> patrons = PatronManager.getAllPatrons();
            for (Patron patron : patrons){
                System.out.println(patron);
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            fail("An unexpected SQLException occured");
        }
    }
}