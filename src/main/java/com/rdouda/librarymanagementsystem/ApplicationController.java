package com.rdouda.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ApplicationController {
    @FXML
    private Label messageLabel;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField quantityField;

    public ApplicationController(){

    }

    @FXML
    private void initialize(){
        TextField[] textFields = new TextField[]{
                titleField,
                authorField,
                isbnField,
                quantityField,
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
                isbnField.getText().isEmpty() ||
                quantityField.getText().isEmpty()
        ){
            messageLabel.setText("Please check book informations.");
        } else {
            String bookTitle = titleField.getText();
            String bookAuthor = authorField.getText();
            String isbn = isbnField.getText();
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                DatabaseManager.addBook(bookTitle, bookAuthor, isbn, quantity, 0);
                messageLabel.setText("Book added successfully.");
            } catch (NumberFormatException numberFormatException){
                messageLabel.setText("Please check book informations.");
            }
        }
    }
}