package com.rdouda.core.library;

import java.time.LocalDate;

public class Borrowing {
    private int borrowingId;
    private int bookId;
    private int patronId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Borrowing(int borrowingId, int bookId, int patronId, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate) {
        this.borrowingId = borrowingId;
        this.bookId = bookId;
        this.patronId = patronId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }
    public Borrowing(int bookId, int patronId, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate) {
        this.bookId = bookId;
        this.patronId = patronId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public Borrowing(){

    }

    public int getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(int borrowingId) {
        this.borrowingId = borrowingId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
