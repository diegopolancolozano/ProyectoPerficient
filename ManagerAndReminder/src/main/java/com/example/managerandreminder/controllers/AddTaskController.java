package com.example.managerandreminder.controllers;

import com.example.managerandreminder.model.CalendarActivity;
import com.example.managerandreminder.model.Category;
import com.example.managerandreminder.model.ColorCustom;
import com.example.managerandreminder.model.Status;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {

    @FXML
    private Label errorLabel;

    @FXML
    private DatePicker date;

    @FXML
    private TextField title, description;

    @FXML
    private ChoiceBox color, category;

    @FXML
    public void addTask(){
        String titleText = title.getText();
        String descriptionText = description.getText();
        String colorSelected = (String) color.getValue();
        String categorySelected = (String) category.getValue();
        LocalDate selectedDate = date.getValue();
        if(titleText == null || titleText.equals("")){
            errorLabel.setText("Invalid title");
        }else if(descriptionText == null || descriptionText.equals("")){
            errorLabel.setText("Invalid description");
        }else if(colorSelected == null){
            errorLabel.setText("Invalid color");
        }else if (categorySelected == null) {
            errorLabel.setText("Invalid category");
        }else if(date.getValue() == null) {
            errorLabel.setText("Invalid date");
        }else{
            ZonedDateTime zonedDateTime = selectedDate.atStartOfDay().atZone(ZonedDateTime.now().getZone());
            ColorCustom type = ColorCustom.BLUE;
            Category cat = Category.SOCIAL;
            switch (colorSelected){
                case "Blue" -> type = ColorCustom.BLUE;
                case "Red" -> type = ColorCustom.RED;
                case "Yellow" -> type = ColorCustom.YELLOW;
                case "Green" -> type = ColorCustom.GREEN;
                case "Pink" -> type = ColorCustom.PINK;
                case "Orange" -> type = ColorCustom.ORANGE;
                case "Purple" -> type = ColorCustom.PURPLE;
                case "Brown" -> type = ColorCustom.BROWN;
            }
            switch (categorySelected){
                case "Medical" -> cat = Category.MEDICAL;
                case "Laboral" -> cat = Category.LABORAL;
                case "Academic" -> cat = Category.ACADEMIC;
                case "Social" -> cat = Category.SOCIAL;
                case "Alimentation" -> cat = Category.ALIMENTATION;
                case "Other" -> cat = Category.OTHER;
            }

            CalendarActivity activity = new CalendarActivity(zonedDateTime, titleText, descriptionText, type, cat, Status.TO_DO);

            UIcontroller.getInstance().addActivityToUser(activity);

            errorLabel.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        color.getItems().addAll("Blue", "Red", "Green", "Yellow", "Pink", "Orange", "Purple", "Brown");
        category.getItems().addAll("Medical", "Laboral", "Academic", "Social", "Alimentation", "Other");
    }

}
