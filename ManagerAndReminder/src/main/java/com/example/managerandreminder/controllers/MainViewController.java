package com.example.managerandreminder.controllers;

import com.example.managerandreminder.HelloApplication;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Text year;

    @FXML
    private Text month;

    private CalendarController calendarController;

    @FXML
    private AnchorPane calendar;

    @FXML
    private ChoiceBox choiceBoxStatus;

    @FXML
    private ChoiceBox choiceBoxCategory;

    @FXML
    public void onAdd(){
        HelloApplication.openWindow("AddTask.fxml", "AddTask");
        handleCloseButtonAction();
    }

    @FXML
    public void onFilter(){
        String selectedCategory = (String) choiceBoxCategory.getValue();
        String selectedStatus = (String) choiceBoxStatus.getValue();

        UIcontroller.getInstance().setFilterCategory(selectedCategory);
        UIcontroller.getInstance().setFilterStatus(selectedStatus);

        //calendarController.drawCalendar();

        HelloApplication.openWindow("MainView.fxml", "Activities", 1000, 650);
        handleCloseButtonAction();
    }

    private void handleCloseButtonAction() {
        // Obtén el Stage actual desde cualquier nodo de la escena
        Stage stage = (Stage) choiceBoxCategory.getScene().getWindow();

        // Cierra la ventana
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxCategory.getItems().addAll("All", "Medical", "Laboral", "Academic", "Social", "Alimentation", "Other");
        choiceBoxStatus.getItems().addAll("All", "To do", "Done", "Cancelled");

        choiceBoxCategory.setValue(UIcontroller.getInstance().getFilterCategory());
        choiceBoxStatus.setValue(UIcontroller.getInstance().getFilterStatus());

    }

}