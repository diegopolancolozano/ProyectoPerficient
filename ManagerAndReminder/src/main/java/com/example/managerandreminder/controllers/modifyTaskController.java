package com.example.managerandreminder.controllers;

import com.example.managerandreminder.HelloApplication;
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
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class modifyTaskController implements Initializable {

    public modifyTaskController(){
        activity = UIcontroller.getInstance().getActualActivity();
    }

    private CalendarActivity activity;

    @FXML
    private Label errorLabel;

    @FXML
    private DatePicker date;

    @FXML
    private TextField title, description;

    @FXML
    private ChoiceBox color, category, statusActivity;

    @FXML
    public void modify(){
        String titleText = title.getText();
        String descriptionText = description.getText();
        String colorSelected = (String) color.getValue();
        String categorySelected = (String) category.getValue();
        String statusSelected = (String) statusActivity.getValue();
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
            Status stat = Status.TO_DO;
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
            switch (statusSelected){
                case "To do" -> stat = Status.TO_DO;
                case "Done" -> stat = Status.DONE;
                case "Cancelled" -> stat = Status.CANCELED;
                case "In progress" -> stat = Status.IN_PROGRESS;
            }

            CalendarActivity newActivity = new CalendarActivity(zonedDateTime, titleText, descriptionText, type, cat, stat);

            UIcontroller.getInstance().changeActivity(activity, newActivity);

            errorLabel.setText("");

            HelloApplication.openWindow("MainView.fxml", "Activities", 1000, 650);

            Stage stage = (Stage) title.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        color.getItems().addAll("Blue", "Red", "Green", "Yellow", "Pink", "Orange", "Purple", "Brown");
        category.getItems().addAll("Medical", "Laboral", "Academic", "Social", "Alimentation", "Other");
        statusActivity.getItems().addAll("To do", "Done", "Cancelled", "In progress");

        switch (activity.getColor()){
            case ColorCustom.BLUE -> color.setValue("Blue");
            case ColorCustom.GREEN -> color.setValue("Green");
            case ColorCustom.RED -> color.setValue("Red");
            case ColorCustom.YELLOW -> color.setValue("Yellow");
            case ColorCustom.PINK -> color.setValue("Pink");
            case ColorCustom.ORANGE -> color.setValue("Orange");
            case ColorCustom.PURPLE -> color.setValue("Purple");
            case ColorCustom.BROWN -> color.setValue("Brown");
        }

        category.setValue(activity.getCategory());

        statusActivity.setValue(activity.getStatus());

        title.setText(activity.getTitle());

        description.setText(activity.getDescription());

        date.setValue(activity.getDate().toLocalDate());
    }

    @FXML
    public void delete(){
        UIcontroller.getInstance().deleteActivity(activity);
        HelloApplication.openWindow("MainView.fxml", "Activities", 1000, 650);
        Stage stage = (Stage) title.getScene().getWindow();
        stage.close();
    }

}
