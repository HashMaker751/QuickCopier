package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("startScene.fxml"));
        primaryStage.setTitle("QuickCopier");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.getIcons().add(new Image(String.valueOf(new File("icon2.png").toURI().toURL())));
        primaryStage.show();
    }
}
