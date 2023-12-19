package com.example.managerandreminder.controllers;

import com.example.managerandreminder.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private AnchorPane calendar;

    @FXML
    private ChoiceBox choiceBoxStatus;

    @FXML
    private ChoiceBox choiceBoxCategory;

    @FXML
    public void onAdd(){
        HelloApplication.openWindow("AddTask.fxml", "AddTask");
    }

    @FXML
    public void onFilter(){
        //TODO filter
    }

    private CalendarController calendarController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}