module com.mycompany.bim {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    requires java.sql;
    
    opens controller to javafx.fxml;
    exports controller;
    
//    opens model to javafx.fxml;
//    exports model;
//       
//    opens utils to javafx.fxml;
//    exports utils;

    opens com.mycompany.bim to javafx.fxml;
    exports com.mycompany.bim;
}
