package com.example.managerandreminder.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class User implements Serializable {

    private final String username;

    private final String password;

    private final String email;

    private ArrayList<CalendarActivity> calendarTasks;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.calendarTasks = new ArrayList<CalendarActivity>();
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public ArrayList<CalendarActivity> getTasks() {
        return calendarTasks;
    }

    public void addActivity(CalendarActivity activity) {
        calendarTasks.add(activity);
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return username + " " + password + " " + email;
    }
}
