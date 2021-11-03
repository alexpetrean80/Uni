package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       StackPane root = (StackPane) FXMLLoader.load(getClass().getResource("ProgramSelector.fxml"));
       Scene scene = new Scene(root, 800, 400);
       primaryStage.setScene(scene);
       primaryStage.setTitle("Program Selector");
       primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

