package com.rdouda.librarymanagementsystem;

public class SQLQueries {
    public static final String GET_BOOK = "SELECT * FROM books WHERE isbn = ?";
    public static final String ADD_BOOK = "INSERT INTO books (title, author, isbn, quantity) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_BOOK_QUANTITY = "UPDATE books SET quantity = ? WHERE isbn = ?";
    public static final String REMOVE_BOOK = "DELETE FROM books WHERE isbn = ?";
}
