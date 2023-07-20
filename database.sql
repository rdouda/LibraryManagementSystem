CREATE TABLE books (
    book_id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    author TEXT NOT NULL,
    isbn TEXT UNIQUE NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 0,
    is_taken INTEGER NOT NULL DEFAULT 0
);

-- Table for storing information about patrons (library members)
CREATE TABLE patrons (
    patron_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    contact_number TEXT NOT NULL,
    membership_id TEXT UNIQUE NOT NULL
);

-- Table for storing borrowing information
CREATE TABLE borrowings (
    borrowing_id INTEGER PRIMARY KEY AUTOINCREMENT,
    book_id INTEGER NOT NULL,
    patron_id INTEGER NOT NULL,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (patron_id) REFERENCES patrons(patron_id)
);

-- Trigger to prevent deleting patrons with active borrowings
CREATE TRIGGER prevent_patron_deletion
BEFORE DELETE ON patrons
FOR EACH ROW
BEGIN
    SELECT
        CASE
            WHEN EXISTS (SELECT 1 FROM borrowings WHERE patron_id = OLD.patron_id AND return_date IS NULL)
            THEN RAISE (ABORT, 'Cannot delete patron with active borrowings')
        END;
END;