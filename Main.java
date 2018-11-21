package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        stage.setTitle("Snake vs Block");
        stage.setScene(new Scene(root, 335, 600));
        stage.show();
    }
}