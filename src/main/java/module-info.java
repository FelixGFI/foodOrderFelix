module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires kernel;
    requires layout;
    requires io;
    requires java.desktop;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.foodOrder;
    opens com.example.demo.foodOrder to javafx.fxml;
    exports com.example.demo.foodOrder.gui;
    opens com.example.demo.foodOrder.gui to javafx.fxml;
}