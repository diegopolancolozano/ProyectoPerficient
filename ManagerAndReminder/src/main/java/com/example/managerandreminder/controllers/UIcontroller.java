package com.example.managerandreminder.controllers;

import com.example.managerandreminder.model.CalendarActivity;
import com.example.managerandreminder.model.User;
import javafx.scene.Scene;

import java.util.ArrayList;

public class UIcontroller {

    private static UIcontroller instance;

    private User actualUser;

    public static UIcontroller getInstance(){
        if(instance==null){
            instance = new UIcontroller();
        }
        return instance;
    }

    public void addActivityToUser(CalendarActivity activity){
        actualUser.addActivity(activity);
        UserDataGestor.getInstance().overrideFile();
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
                actualUser = users.get(i);
            }
        }
        return done;
    }

    public ArrayList<CalendarActivity> getActivities(){
        return actualUser.getTasks();
    }
}