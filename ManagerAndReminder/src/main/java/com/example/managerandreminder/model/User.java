package com.example.managerandreminder.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class User implements Serializable {

    private final String username;

    private final String password;

    private final String email;

    private ArrayList<Task> tasks;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.tasks = new ArrayList<Task>();
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public boolean addTask(String title, String description, Color color, Category category,
                           Calendar dueDate, Status status, ArrayList<DayOfWeek> daysOfWeek){
        Task newTask = new Task(title, description, color, category,dueDate, status, daysOfWeek);
        tasks.add(newTask);
        return true;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return username + " " + password + " " + email;
    }
}
