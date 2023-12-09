package com.example.managerandreminder.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController{

    private UserDataGestor dataGestor;

    @FXML
    private Label errorText;

    @FXML
    private Label loginText;

    @FXML
    private TextField inputUsername;

    @FXML
    private TextField inputPassword;

    @FXML
    protected void onLogin() {
        errorText.setText("Error");
    }

    @FXML
    protected void onSignin() {
        errorText.setText("Error");
    }


    public void initialize() {

    }

}