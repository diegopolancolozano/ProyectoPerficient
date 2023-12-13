package com.example.managerandreminder.controllers;

import com.example.managerandreminder.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class registerController {

    @FXML
    private Label errorText;

    @FXML
    private TextField inputUsername;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputPassword;

    @FXML
    private void onSignin(){
        ArrayList<User> users = UserDataGestor.getInstance().getUsers();
        boolean done = false;
        String usern = inputUsername.getText();
        String email = inputEmail.getText();
        String passw = inputPassword.getText();

        for (int i = 0; i < users.size() && !done; i++) {
            if(users.get(i).getUsername().equals(usern)){
                done = true;
                errorText.setText("Nombre de usuario en uso");
            }else if(users.get(i).getEmail().equals(email)){
                done = true;
                errorText.setText("Correo en uso");
            }
        }
        if(!done){
            users.add(new User(usern, passw, email));
            UserDataGestor.getInstance().setUsers(users);

            UserDataGestor.getInstance().overrideFile();

            errorText.setText("Creado");
        }
    }
}
