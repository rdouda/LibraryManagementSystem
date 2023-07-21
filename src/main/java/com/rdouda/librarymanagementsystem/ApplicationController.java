package com.rdouda.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

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
            try{
                DatabaseManager.addBook(bookTitle, bookAuthor, isbn, 0);
                messageLabel.setText("Book added successfully.");
            }catch (SQLException sqlException){
                messageLabel.setText("Error adding book." + sqlException.getMessage());
            }
        }
    }
}