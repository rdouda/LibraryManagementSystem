module com.rdouda.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.rdouda.librarymanagementsystem to javafx.fxml;
    exports com.rdouda.librarymanagementsystem;
}