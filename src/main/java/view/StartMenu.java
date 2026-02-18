package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StartMenu extends Application{

    public void start(Stage stage) throws Exception {
        try {
            URL url = getClass().getResource("/FXML/Main.fxml");
            if (url == null) {
                throw new IOException("Cannot find FXML file at /FXML/firstMenu.fxml");
            }

            BorderPane borderPane = FXMLLoader.load(url);
            stage.setScene(new Scene(borderPane));
            stage.show();
            stage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(Main.stage);
    }

    public void register(MouseEvent mouseEvent) throws Exception {
        new RegisterMenu().start(Main.stage);
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
