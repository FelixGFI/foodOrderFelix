module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires  kernel;
    requires layout;
    requires io;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}