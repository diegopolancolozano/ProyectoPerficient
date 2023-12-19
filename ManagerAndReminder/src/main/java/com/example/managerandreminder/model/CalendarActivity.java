package com.example.managerandreminder.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class CalendarActivity implements Serializable {
    private ZonedDateTime date;
    private String title;

    private String description;

    private ColorCustom colorCustom;

    private Category category;

    private Status status;

    public CalendarActivity(ZonedDateTime date, String title, String description, ColorCustom colorCustom, Category category, Status status) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.colorCustom = colorCustom;
        this.category = category;
        this.status = status;
    }

    public CalendarActivity(ZonedDateTime date, String title) {
        this.date = date;
        this.title = title;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", clientName='" + title + '\'' +
                '}';
    }

    public ColorCustom getColor() {
        return colorCustom;
    }

    public String getCategory() {
        String out = "";
        switch (category) {
            case Category.OTHER -> out = "Other";
            case Category.SOCIAL -> out = "Social";
            case Category.ALIMENTATION -> out = "Alimentation";
            case Category.ACADEMIC -> out = "Academic";
            case Category.LABORAL -> out = "Laboral";
            case Category.MEDICAL -> out = "Medical";
        }
        return out;
    }

    public String getStatus() {
        String out = "";
        switch (status) {
            case Status.DONE -> out = "Done";
            case Status.CANCELED -> out = "Cancelled";
            case Status.TO_DO -> out = "To do";
            case Status.IN_PROGRESS -> out = "In progress";
        }
        return out;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
