package view;

import enums.UnitCard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Main.stage = stage;

        try {
            URL url = Main.class.getResource("/FXML/Main.fxml");
            BorderPane borderpane = FXMLLoader.load(Objects.requireNonNull(url));
            Scene scene = new Scene(borderpane);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(String.valueOf(Main.class.getResource("/Image/icon.png"))));
            stage.setTitle("Gwent");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
