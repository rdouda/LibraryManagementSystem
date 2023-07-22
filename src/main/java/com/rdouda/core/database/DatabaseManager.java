package com.rdouda.core.database;

import com.rdouda.core.library.Book;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
    private static final String databaseFileName = "Sqlite3.db";
    private static final String databaseFilePath = "jdbc:sqlite:" + databaseFileName;
    public static Connection connection = null;
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

    public static void closeConnection(){
        try {
            connection.close();
        } catch(SQLException sqlException){
            System.out.println("Error closing database connection.");
        }
    }
}
