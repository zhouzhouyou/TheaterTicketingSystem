package controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.Theater;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = MainController.loader.load();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                Theater.getInstance().close();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
