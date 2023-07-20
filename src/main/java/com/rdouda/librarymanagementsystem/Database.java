package com.rdouda.librarymanagementsystem;
import java.sql.*;

public class Database {
    private Connection database = null;
    public Database(){
        this.database = connect();
    }

    private Connection connect(){
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

    public void addBook(String title, String author, String isbn, int quantity, int isTaken){
        try{
            PreparedStatement getBookStatement = this.database.prepareStatement(SQLQueries.GET_BOOK);
            getBookStatement.setString(1, isbn);
            ResultSet resultSet = getBookStatement.executeQuery();
            if (resultSet.next()){

            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    public void disconnect(){
        try{
            if (this.database != null) {
                this.database.close();
                System.out.println("Disconnected from database.");
            }
        } catch (SQLException sqlException){
            System.out.println("Error closing the connection.");
        }
    }
}
