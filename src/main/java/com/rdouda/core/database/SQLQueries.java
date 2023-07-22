package com.rdouda.core.database;

public class SQLQueries {
    // DATABASE
    public static final String BOOKS_TABLE =
            "CREATE TABLE books (" +
            "book_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title TEXT NOT NULL," +
            "author TEXT NOT NULL," +
            "isbn TEXT UNIQUE NOT NULL," +
            "is_taken INTEGER NOT NULL DEFAULT 0);";

    public static final String PATRON_TABLE =
            "CREATE TABLE patrons (" +
            "patron_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT NOT NULL," +
            "contact_number TEXT NOT NULL," +
            "membership_id TEXT UNIQUE NOT NULL);";

    public static final String BORROWINGS_TABLE =
            "CREATE TABLE borrowings (" +
            "borrowing_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "book_id INTEGER NOT NULL," +
            "patron_id INTEGER NOT NULL," +
            "borrow_date DATE NOT NULL," +
            "due_date DATE NOT NULL," +
            "return_date DATE," +
            "FOREIGN KEY (book_id) REFERENCES books(book_id)," +
            "FOREIGN KEY (patron_id) REFERENCES patrons(patron_id));";

    public static final String PREVENT_PATRON_DELETION =
            "CREATE TRIGGER prevent_patron_deletion" +
            "   BEFORE DELETE ON patrons" +
            "   FOR EACH ROW" +
            "   BEGIN" +
            "       SELECT" +
            "           CASE" +
            "               WHEN EXISTS (SELECT 1 FROM borrowings WHERE patron_id = OLD.patron_id AND return_date IS NULL)" +
            "               THEN RAISE (ABORT, 'Cannot delete patron with active borrowings')" +
            "           END;" +
            "END;";

    // BOOK SQL QUERIES
    public static final String GET_BOOK = "SELECT * FROM books WHERE isbn = ?";
    public static final String ADD_BOOK = "INSERT INTO books (title, author, isbn, is_taken) VALUES (?, ?, ?, ?)";
    public static final String REMOVE_BOOK = "DELETE FROM books WHERE isbn = ?";
    public static final String GET_BOOKS = "SELECT * FROM books;";
    public static final String REMOVE_ALL_BOOKS = "DELETE FROM books;";

    // BORROWINGS SQL QUERIES
    public static final String GET_BORROWINGS_BY_PATRON_ID = "SELECT * FROM borrowings WHERE patron_id = ?";
    public static final String GET_ALL_BORROWINGS = "SELECT * FROM borrowings";
    public static final String GET_BORROWINGS_BY_BOOK_ID = "SELECT * FROM borrowings WHERE book_id = ?";
    public static final String REMOVE_BORROWING_BY_PATRON_AND_BOOK = "DELETE FROM borrowings WHERE book_id = ? AND patron_id = ?";
    public static final String REMOVE_ALL_BORROWINGS_BY_PATRON = "DELETE FROM borrowings WHERE patron_id = ?";
    public static final String ADD_BORROWING = "INSERT INTO borrowings (book_id, patron_id, borrow_date, due_date, return_date) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_BORROWING_RETURN_DATE = "UPDATE borrowings SET return_date = ? WHERE borrowing_id = ?";
    public static final String GET_OVERDUE_BORROWINGS = "SELECT * FROM borrowings WHERE due_date < ? AND return_date IS NULL";
    public static final String GET_BORROWINGS_BY_RETURN_DATE_RANGE = "SELECT * FROM borrowings WHERE return_date BETWEEN ? AND ?";
    public static final String GET_BORROWINGS_WITH_DETAILS = "SELECT b.*, bk.title, bk.author, p.name AS patron_name FROM borrowings b JOIN books bk ON b.book_id = bk.book_id JOIN patrons p ON b.patron_id = p.patron_id";

    // PATRON QUERIES
    public static final String ADD_PATRON = "INSERT INTO patrons (name, contact_number, membership_id) VALUES (?, ?, ?)";
    public static final String GET_ALL_PATRONS = "SELECT * FROM patrons";
    public static final String REMOVE_ALL_PATRONS = "DELETE FROM patrons;";
    public static final String GET_PATRON_BY_MEMBERSHIP_ID = "SELECT * FROM patrons WHERE membership_id = ?";
    public static final String UPDATE_PATRON = "UPDATE patrons SET name = ?, contact_number = ?, phone = ? WHERE patron_id = ?";
    public static final String DELETE_PATRON_BY_MEMBERSHIP_ID = "DELETE FROM patrons WHERE membership_id = ?";
    public static final String GET_PATRONS_BY_NAME = "SELECT * FROM patrons WHERE name LIKE ?";
    public static final String GET_PATRONS_BY_CONTACT_NUMBER = "SELECT * FROM patrons WHERE contact_number = ?";
    public static final String GET_OVERDUE_PATRONS = "SELECT p.* FROM patrons p JOIN borrowings b ON p.patron_id = b.patron_id WHERE b.due_date < ? AND b.return_date IS NULL";
    public static final String GET_PATRON_COUNT = "SELECT COUNT(*) AS patron_count FROM patrons";
}
