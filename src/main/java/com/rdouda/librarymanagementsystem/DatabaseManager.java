package com.rdouda.librarymanagementsystem;
import java.sql.*;

public class DatabaseManager {
    private static Connection getConnection(){
        Connection connection = null;
        try {
            String databaseFilePath = "jdbc:sqlite:sqlite3.db";
            connection = DriverManager.getConnection(databaseFilePath);
            System.out.println("Connected the database.");
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException("Failed to connect to the database.");
        }
        return connection;
    }

    public static void addBook(String title, String author, String isbn, int isTaken) throws SQLException {
        Connection database = getConnection();
        PreparedStatement addBookStatement = database.prepareStatement(SQLQueries.ADD_BOOK);
        addBookStatement.setString(1, title);
        addBookStatement.setString(2, author);
        addBookStatement.setString(3, isbn);
        addBookStatement.setInt(4, isTaken);
        addBookStatement.executeUpdate();
        database.close();
    }

    public static void removeBook(String isbn, int quantity) throws SQLException {
        Connection database = getConnection();
        PreparedStatement removeBookStatement = database.prepareStatement(SQLQueries.REMOVE_BOOK);
        removeBookStatement.setString(1, isbn);
        removeBookStatement.executeUpdate();
        database.close();
    }
}
