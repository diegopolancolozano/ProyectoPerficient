package com.example.managerandreminder.controllers;

import com.example.managerandreminder.HelloApplication;
import com.example.managerandreminder.model.CalendarActivity;
import com.example.managerandreminder.model.ColorCustom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CalendarController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    /*private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //List of activities for a given month
        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        if(calendarActivities != null){
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }*/



    public void drawCalendar() {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        // Obtiene los valores seleccionados de los filtros
        String selectedCategory = UIcontroller.getInstance().getFilterCategory();
        String selectedStatus = UIcontroller.getInstance().getFilterStatus();

        // Filtra las actividades según la categoría y el estado seleccionados
        Map<Integer, List<CalendarActivity>> calendarActivityMap = filterActivitiesByCategoryAndStatus(selectedCategory, selectedStatus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        // Check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        if (calendarActivities != null) {
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    // Método para filtrar las actividades por categoría y estado
    private Map<Integer, List<CalendarActivity>> filterActivitiesByCategoryAndStatus(String selectedCategory, String selectedStatus) {
        // Obtiene todas las actividades
        List<CalendarActivity> allActivities = UIcontroller.getInstance().getActivities();

        // Filtra por categoría y estado
        if (!"All".equals(selectedCategory)) {
            allActivities = allActivities.stream()
                    .filter(activity -> selectedCategory.equals(activity.getCategory().toString()))
                    .collect(Collectors.toList());
        }

        if (!"All".equals(selectedStatus)) {
            allActivities = allActivities.stream()
                    .filter(activity -> selectedStatus.equals(activity.getStatus().toString()))
                    .collect(Collectors.toList());
        }

        // Agrupa las actividades por día
        return createCalendarMap(allActivities);
    }


    private void createCalendarActivity(List<CalendarActivity> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();

        for (int k = 0; k < calendarActivities.size(); k++) {
            if (k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    // On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
                break;
            }

            CalendarActivity activity = calendarActivities.get(k);
            ColorCustom textColor = activity.getColor();

            Text text = new Text(activity.getTitle() + "\n" + activity.getCategory());

            // Cambiar el color del texto
            String colorStyle = getColorStyle(textColor);
            text.setStyle(colorStyle);

            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                // On Text clicked
                UIcontroller.getInstance().setActualActivity(activity);
                //modifyTaskController window = new modifyTaskController();
                //window.openWindow();
                HelloApplication.openWindow("ModifyTask.fxml", "modify", 640, 510);
                Stage stage = (Stage) text.getScene().getWindow();

                stage.close();
            });
        }

        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        stackPane.getChildren().add(calendarActivityBox);
    }

    private String getColorStyle(ColorCustom color) {
        // Mapear los valores de enumeración a colores hexadecimales
        switch (color) {
            case BLUE:
                return "-fx-fill: #0000FF;";
            case RED:
                return "-fx-fill: #FF0000;";
            case GREEN:
                return "-fx-fill: #00FF00;";
            case YELLOW:
                return "-fx-fill: #FFFF00;";
            case PINK:
                return "-fx-fill: #FFC0CB;";
            case ORANGE:
                return "-fx-fill: #FFA500;";
            case PURPLE:
                return "-fx-fill: #800080;";
            case BROWN:
                return "-fx-fill: #A52A2A;";
            default:
                return "-fx-fill: GRAY;"; // Color predeterminado en caso de no coincidencia
        }
    }


    private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        for (CalendarActivity activity: calendarActivities) {
            int activityDate = activity.getDate().getDayOfMonth();
            if(!calendarActivityMap.containsKey(activityDate)){
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<CalendarActivity> OldListByDate = calendarActivityMap.get(activityDate);

                List<CalendarActivity> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return  calendarActivityMap;
    }

    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        // Obtener el año y el mes de la fecha proporcionada
        int year = dateFocus.getYear();
        int month = dateFocus.getMonthValue();

        // Crear una lista para almacenar las actividades del mes
        List<CalendarActivity> calendarActivities = new ArrayList<>();

        // Obtener la lista de actividades del usuario actual
        List<CalendarActivity> userActivities = UIcontroller.getInstance().getActivities();

        // Filtrar las actividades para obtener solo las del mes actual
        for (CalendarActivity activity : userActivities) {
            if (activity.getDate().getYear() == year && activity.getDate().getMonthValue() == month) {
                calendarActivities.add(activity);
            }
        }

        // Utilizar el método createCalendarMap para organizar las actividades en un mapa por día
        return createCalendarMap(calendarActivities);
    }



}