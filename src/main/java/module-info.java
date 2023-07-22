module com.rdouda.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.rdouda.core to javafx.fxml;
    exports com.rdouda.core;
    exports com.rdouda.core.library;
    opens com.rdouda.core.library to javafx.fxml;
    exports com.rdouda.core.database;
    opens com.rdouda.core.database to javafx.fxml;
}