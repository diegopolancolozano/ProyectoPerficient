package com.example.managerandreminder.controllers;

import com.example.managerandreminder.model.CalendarActivity;
import com.example.managerandreminder.model.Category;
import com.example.managerandreminder.model.Status;
import com.example.managerandreminder.model.User;
import javafx.scene.Scene;

import java.util.ArrayList;

public class UIcontroller {

    private static UIcontroller instance;

    private User actualUser;
    private CalendarActivity actualActivity;

    private String filterCategory;

    private String filterStatus;

    private ArrayList<User> users;

    public static UIcontroller getInstance(){
        if(instance==null){
            instance = new UIcontroller();
        }
        return instance;
    }

    public void setActualActivity(CalendarActivity activity) {
        this.actualActivity = activity;
    }

    public CalendarActivity getActualActivity() {
        return actualActivity;
    }

    public void addActivityToUser(CalendarActivity activity){
        actualUser.addActivity(activity);
        UserDataGestor.getInstance().overrideFile();
    }

    public UIcontroller() {
        this.users = UserDataGestor.getInstance().getUsers();
        this.filterCategory = "All";
        this.filterStatus = "All";
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

    public void changeActivity(CalendarActivity activity, CalendarActivity newActivity) {
        boolean done = false;
        ArrayList<CalendarActivity> activities = actualUser.getTasks();
        for(int i=0; i<activities.size() && !done; i++){
            if(activities.get(i).equals(activity)){
                activities.set(i, newActivity);
                done = true;
            }
        }
        UserDataGestor.getInstance().overrideFile();
    }

    public void deleteActivity(CalendarActivity activity) {
        boolean done = false;
        ArrayList<CalendarActivity> activities = actualUser.getTasks();
        for(int i=0; i<activities.size() && !done; i++){
            if(activities.get(i).equals(activity)){
                activities.remove(i);
                done = true;
            }
        }
        UserDataGestor.getInstance().overrideFile();
    }

    public String getFilterCategory() {
        return filterCategory;
    }

    public void setFilterCategory(String filterCategory) {
        this.filterCategory = filterCategory;
    }

    public String getFilterStatus() {
        return filterStatus;
    }

    public void setFilterStatus(String filterStatus) {
        this.filterStatus = filterStatus;
    }
}