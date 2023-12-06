module com.example.managerandreminder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.managerandreminder to javafx.fxml;
    exports com.example.managerandreminder;
    exports com.example.managerandreminder.controllers;
    opens com.example.managerandreminder.controllers to javafx.fxml;
}