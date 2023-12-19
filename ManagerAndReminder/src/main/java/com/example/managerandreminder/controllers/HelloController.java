package com.example.managerandreminder.controllers;

import com.example.managerandreminder.HelloApplication;
import com.example.managerandreminder.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button loginButton;

    @FXML
    protected void onLogin() {
        if(!uiController.isValidLogin(inputUsername.getText(), inputPassword.getText())){
            errorText.setText("Error, No existe una cuenta con esas credenciales");
        }else{
            //HelloApplication.openWindow("Calendar.fxml", "Activities", 740, 640);
            HelloApplication.openWindow("MainView.fxml", "Activities", 880, 640);
            handleCloseButtonAction();
        }
    }

    @FXML
    protected void onSignin() {
        HelloApplication.openWindow("register.fxml", "register");
    }

    @FXML
    private void handleCloseButtonAction() {
        // Obtén el Stage actual desde cualquier nodo de la escena
        Stage stage = (Stage) loginButton.getScene().getWindow();

        // Cierra la ventana
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uiController = UIcontroller.getInstance();
    }

}