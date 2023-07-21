package com.rdouda.librarymanagementsystem;
import java.io.File;
import java.sql.*;

public class DatabaseManager {
    private static final String databaseFileName = "Sqlite3.db";
    private static final String databaseFilePath = "jdbc:sqlite:" + databaseFileName;
    private static Connection connection = null;
    private static Connection connectToDatabase(){
        DatabaseManager.database();
        if (connection != null)
            return connection;
        try {
            connection = DriverManager.getConnection(databaseFilePath);
            System.out.println("Connected the database.");
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return connection;
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
                System.out.println(sqlException.getMessage());
            }
        }
    }

    public static boolean addBook(String title, String author, String isbn, int isTaken) throws SQLException {
        Connection database = connectToDatabase();
        PreparedStatement addBookStatement = database.prepareStatement(SQLQueries.ADD_BOOK);
        addBookStatement.setString(1, title);
        addBookStatement.setString(2, author);
        addBookStatement.setString(3, isbn);
        addBookStatement.setInt(4, isTaken);
        int rowsAffected = addBookStatement.executeUpdate();
        database.close();
        return rowsAffected > 0;
    }

    public static boolean removeBook(String isbn) throws SQLException {
        Connection database = connectToDatabase();
        PreparedStatement removeBookStatement = database.prepareStatement(SQLQueries.REMOVE_BOOK);
        removeBookStatement.setString(1, isbn);
        int rowsAffected = removeBookStatement.executeUpdate();
        database.close();
        return rowsAffected > 0;
    }

    public static int updateBook(String isbn) throws SQLException {
        return 0;
    }
}
