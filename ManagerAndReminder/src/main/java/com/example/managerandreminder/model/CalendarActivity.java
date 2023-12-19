package com.example.managerandreminder.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class CalendarActivity implements Serializable {
    private ZonedDateTime date;
    private String tittle;

    private String description;

    private ColorCustom colorCustom;

    private Category category;

    private Status status;

    public CalendarActivity(ZonedDateTime date, String tittle, String description, ColorCustom colorCustom, Category category, Status status) {
        this.date = date;
        this.tittle = tittle;
        this.description = description;
        this.colorCustom = colorCustom;
        this.category = category;
        this.status = status;
    }

    public CalendarActivity(ZonedDateTime date, String Tittle) {
        this.date = date;
        this.tittle = Tittle;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }


    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", clientName='" + tittle + '\'' +
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
}
