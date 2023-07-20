module com.rdouda.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.rdouda.librarymanagementsystem to javafx.fxml;
    exports com.rdouda.librarymanagementsystem;
}