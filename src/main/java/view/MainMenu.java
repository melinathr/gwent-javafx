package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;
import java.net.URL;

public class MainMenu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            URL url = getClass().getResource("/FXML/MainMenu.fxml");
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

    public void logout(MouseEvent mouseEvent) {
        Player.setLoggedInUser(null);
        try {
            (new StartMenu()).start(Main.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileMenu(MouseEvent mouseEvent) throws IOException {
        new ProfileMenu().start(Main.stage);
    }

    public void preGameMenu(MouseEvent mouseEvent) throws Exception {
        new PreGameMenu().start(Main.stage);
    }

    public void rankingMenu(MouseEvent mouseEvent) throws Exception{
    new RankingMenu().start(Main.stage);
    }
}
