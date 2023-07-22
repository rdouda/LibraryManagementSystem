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
}