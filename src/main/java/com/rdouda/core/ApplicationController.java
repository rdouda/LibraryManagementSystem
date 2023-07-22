package com.rdouda.core;

import com.rdouda.core.database.BookManager;
import com.rdouda.core.database.DatabaseManager;
import com.rdouda.core.library.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class ApplicationController {
    @FXML
    private Label messageLabel;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField isbnField;

    public ApplicationController(){

    }

    @FXML
    private void initialize(){
        TextField[] textFields = new TextField[]{
                titleField,
                authorField,
                isbnField,
        };
        for (TextField textField : textFields){
            textField.setOnKeyTyped(e -> {
                messageLabel.setText("");
            });
        }
    }

    public void addBook(ActionEvent actionEvent) {
        if (
                titleField.getText().isEmpty() ||
                authorField.getText().isEmpty() ||
                isbnField.getText().isEmpty()
        ){
            messageLabel.setText("Please check book informations.");
        } else {
            String bookTitle = titleField.getText();
            String bookAuthor = authorField.getText();
            String isbn = isbnField.getText();
            Book book = new Book();
            book.setTitle(bookTitle);
            book.setAuthor(bookAuthor);
            book.setIsbn(isbn);
            try{
                if(BookManager.addBook(book))
                    messageLabel.setText("Book added successfully.");
                else
                    messageLabel.setText("Book already exists.");
            }catch (SQLException sqlException){
                messageLabel.setText("Error adding book." + sqlException.getMessage());
            }
        }
    }

    public void removeBook(ActionEvent actionEvent) {
        try{
            if(BookManager.removeBook(isbnField.getText()))
                messageLabel.setText("Book removed: " + isbnField.getText());
            else
                messageLabel.setText("Book not found: " + isbnField.getText());
        }catch (SQLException sqlException){
            messageLabel.setText("Error removing book: " + isbnField.getText());
            System.out.println("SQL Error: " + sqlException.getMessage());
        }
    }

    public void getBooks(ActionEvent actionEvent) {
        try {
            ArrayList<Book> books = BookManager.getBooks();
            for(Book book : books){
                System.out.println(book);
            }
        } catch (SQLException sqlException){
            System.out.println("SQL ERROR: " + sqlException.getMessage());
        }
    }
}