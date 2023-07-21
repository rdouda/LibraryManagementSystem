package com.rdouda.librarymanagementsystem;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
    private static final String databaseFileName = "Sqlite3.db";
    private static final String databaseFilePath = "jdbc:sqlite:" + databaseFileName;
    private static Connection connection = null;
    private static void connectToDatabase(){
        if (connection != null)
            return;
        try {
            connection = DriverManager.getConnection(databaseFilePath);
        } catch (SQLException exception){
            throw new RuntimeException("Error connecting to the database.");
        }
    }

    public static void database(){
        File databaseFile = new File(databaseFileName);
        if (!databaseFile.exists()){
            try {
                connection = DriverManager.getConnection(databaseFilePath);
                Statement statement = connection.createStatement();
                statement.execute(SQLQueries.BOOKS_TABLE);
                statement.execute(SQLQueries.PATRON_TABLE);
                statement.execute(SQLQueries.BORROWINGS_TABLE);
                statement.execute(SQLQueries.PREVENT_PATRON_DELETION);
            } catch (SQLException sqlException){
                throw new RuntimeException("Error connecting to the database.");
            }
        }
        connectToDatabase();
    }

    public static Book getBook(String bookIsbn) throws SQLException{
        PreparedStatement getBookStatement = connection.prepareStatement(SQLQueries.GET_BOOK);
        getBookStatement.setString(1, bookIsbn);
        ResultSet resultSet = getBookStatement.executeQuery();
        if (resultSet.next()){
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String isbn = resultSet.getString("isbn");
            return new Book(title, author, isbn);
        }
        return null;
    }

    public static boolean addBook(Book book) throws SQLException {
        PreparedStatement addBookStatement = connection.prepareStatement(SQLQueries.ADD_BOOK);
        addBookStatement.setString(1, book.getTitle());
        addBookStatement.setString(2, book.getAuthor());
        addBookStatement.setString(3, book.getIsbn());
        addBookStatement.setInt(4, book.getIsTaken());
        int rowsAffected = addBookStatement.executeUpdate();
        return rowsAffected > 0;
    }

    public static boolean removeBook(String isbn) throws SQLException {
        PreparedStatement removeBookStatement = connection.prepareStatement(SQLQueries.REMOVE_BOOK);
        removeBookStatement.setString(1, isbn);
        int rowsAffected = removeBookStatement.executeUpdate();
        return rowsAffected > 0;
    }

    public static ArrayList<Book> getBooks() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        Statement getBooksStatement = connection.createStatement();
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
        Statement removeAllBooksStatement = connection.createStatement();
        removeAllBooksStatement.executeUpdate(SQLQueries.REMOVE_ALL_BOOKS);
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch(SQLException sqlException){
            System.out.println("Error closing database connection.");
        }
    }
}
