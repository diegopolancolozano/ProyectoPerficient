package com.example.managerandreminder.controllers;

import com.example.managerandreminder.HelloApplication;
import com.example.managerandreminder.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class HelloController implements Initializable{

    private UIcontroller uiController;

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
        if(!uiController.isValidLogin(inputUsername.getText(), inputPassword.getText())){
            errorText.setText("Error, No existe una cuenta con esas credenciales");
        }else{
            /// TODO: entrar a la aplicación
        }
    }

    @FXML
    protected void onSignin() {
        HelloApplication.openWindow("register.fxml", "register");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uiController = UIcontroller.getInstance();
    }


}