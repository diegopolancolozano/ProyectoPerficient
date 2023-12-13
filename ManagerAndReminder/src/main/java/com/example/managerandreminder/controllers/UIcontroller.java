package com.example.managerandreminder.controllers;

import com.example.managerandreminder.model.User;
import javafx.scene.Scene;

import java.util.ArrayList;

public class UIcontroller {

    public static UIcontroller instance;

    public static UIcontroller getInstance(){
        if(instance==null){
            instance = new UIcontroller();
        }
        return instance;
    }

    private ArrayList<User> users;

    public UIcontroller() {
        this.users = UserDataGestor.getInstance().getUsers();
    }

    public boolean isValidLogin(String username, String password) {
        boolean done = false;
        for(int i = 0; i < users.size() && ! done; i++) {
            if(users.get(i).login(username, password)){
                done = true;
            }
        }
        return done;
    }
}