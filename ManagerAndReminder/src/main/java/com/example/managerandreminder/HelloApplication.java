package com.example.managerandreminder;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.stage.Stage;


        import java.io.IOException;

public class HelloApplication extends Application {

    public HelloApplication() {
    }

    @Override
    public void start(Stage stage) throws IOException {
        openWindow("hello-View.fxml", "inicio");
    }

    public static void openWindow(String fxml, String tittle){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle(tittle);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public static void openWindow(String fxml, String tittle, int width, int height){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            Stage stage = new Stage();
            stage.setTitle(tittle);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}