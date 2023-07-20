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

    public static void addBook(String title, String author, String isbn, int quantity, int isTaken){
        try{
            Connection database = getConnection();
            PreparedStatement getBookStatement = database.prepareStatement(SQLQueries.GET_BOOK);
            getBookStatement.setString(1, isbn);
            ResultSet resultSet = getBookStatement.executeQuery();
            if (resultSet.next()){
                int currentQuantity = resultSet.getInt("quantity");
                int newQuantity = currentQuantity + quantity;
                PreparedStatement updateBookStatement = database.prepareStatement(SQLQueries.UPDATE_BOOK_QUANTITY);
                updateBookStatement.setInt(1, quantity);
                updateBookStatement.setString(2, isbn);
                updateBookStatement.executeUpdate();
            } else {
                PreparedStatement addBookStatement = database.prepareStatement(SQLQueries.ADD_BOOK);
                addBookStatement.setString(1, title);
                addBookStatement.setString(2, author);
                addBookStatement.setString(3, isbn);
                addBookStatement.setInt(4, quantity);
                addBookStatement.executeUpdate();
            }
            database.close();
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    public void removeBook(String isbn, int quantity){
        try{
            Connection database = getConnection();
            PreparedStatement getBookStatement = database.prepareStatement(SQLQueries.GET_BOOK);
            getBookStatement.setString(1, isbn);
            ResultSet resultSet = getBookStatement.executeQuery();
            if (resultSet.next()){
                int currentQuantity = resultSet.getInt("quantity");
                if (quantity >= currentQuantity){
                    PreparedStatement removeBookStatement = database.prepareStatement(SQLQueries.REMOVE_BOOK);
                    removeBookStatement.setString(1, isbn);
                    removeBookStatement.executeUpdate();
                } else {
                    int newQuantity = currentQuantity - quantity;
                    PreparedStatement updateBookStatement = database.prepareStatement(SQLQueries.UPDATE_BOOK_QUANTITY);
                    updateBookStatement.setInt(1, newQuantity);
                    updateBookStatement.setString(2, isbn);
                    updateBookStatement.executeUpdate();
                }
            }
            database.close();
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }
}
