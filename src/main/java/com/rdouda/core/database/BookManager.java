package com.rdouda.core.database;

import com.rdouda.core.library.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookManager {
    public static Book getBook(Book book) throws SQLException {
        PreparedStatement getBookStatement = DatabaseManager.connection.prepareStatement(SQLQueries.GET_BOOK);
        getBookStatement.setString(1, book.getIsbn());
        ResultSet resultSet = getBookStatement.executeQuery();
        if (resultSet.next()){
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String isbn = resultSet.getString("isbn");
            return new Book(title, author, isbn);
        }
        return null;
    }

    public static void addBook(Book book) throws SQLException {
        PreparedStatement addBookStatement = DatabaseManager.connection.prepareStatement(SQLQueries.ADD_BOOK);
        addBookStatement.setString(1, book.getTitle());
        addBookStatement.setString(2, book.getAuthor());
        addBookStatement.setString(3, book.getIsbn());
        addBookStatement.setInt(4, book.getIsTaken());
        int rowsAffected = addBookStatement.executeUpdate();
    }

    public static void removeBook(Book book) throws SQLException {
        PreparedStatement removeBookStatement = DatabaseManager.connection.prepareStatement(SQLQueries.REMOVE_BOOK);
        removeBookStatement.setString(1, book.getIsbn());
        int rowsAffected = removeBookStatement.executeUpdate();
    }

    public static ArrayList<Book> getBooks() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        Statement getBooksStatement = DatabaseManager.connection.createStatement();
        ResultSet resultSet = getBooksStatement.executeQuery(SQLQueries.GET_BOOKS);
        while(resultSet.next()){
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String isbn = resultSet.getString("isbn");
            int isTaken = resultSet.getInt("is_taken");
            Book book = new Book(id, title, author, isbn, isTaken);
            books.add(book);
        }
        return books;
    }

    public static void removeAllBooks() throws SQLException {
        Statement removeAllBooksStatement = DatabaseManager.connection.createStatement();
        removeAllBooksStatement.executeUpdate(SQLQueries.REMOVE_ALL_BOOKS);
    }
}
