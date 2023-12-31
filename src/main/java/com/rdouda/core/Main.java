package com.rdouda.core;

import com.rdouda.core.database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("application-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Library");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        DatabaseManager.database();
        launch();
        DatabaseManager.closeConnection();
    }
}