package com.example.managerandreminder.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Task {

    private String title;

    private String description;

    private Color color;

    private Category category;

    private Calendar dueDate;

    private Status status;

    private ArrayList<DayOfWeek> daysOfWeek;

    public Task(String title, String description, Color color, Category category,
                Calendar dueDate, Status status, ArrayList<DayOfWeek> daysOfWeek) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.category = category;
        this.dueDate = dueDate;
        this.status = status;
        this.daysOfWeek = daysOfWeek;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Color getColor() {
        return color;
    }

    public Category getCategory() {
        return category;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }
}
