package com.example.managerandreminder.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HelloController{
    @FXML
    private Label errorText;

    @FXML
    private Label loginText;

    @FXML
    protected void onHelloButtonClick() {
        errorText.setText("Welcome to JavaFX Application!");
    }


    public void initialize() {

    }

}