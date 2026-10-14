module com.senai.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.senai.javafx to javafx.fxml;
    exports com.senai.javafx;
}