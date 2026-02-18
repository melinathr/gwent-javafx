package view;

import controller.PreGameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Game;
import model.Player;
import model.PlayerSet;

import java.net.URL;

public class PreGameMenu extends Application {
    public TextField rival;
    private final PreGameController controller = new PreGameController();

    @Override
    public void start(Stage stage) throws Exception {
        URL url;
        if (Player.getTurn().equals(Player.getLoggedInUser())) {
            url = getClass().getResource("/FXML/PreGame.fxml");
        } else {
            url = getClass().getResource("/FXML/PreGamePlayerTwo.fxml");
        }

        assert url != null;
        BorderPane borderPane = FXMLLoader.load(url);
        stage.setScene(new Scene(borderPane));
        stage.show();
    }

    public void back() throws Exception {
        (new MainMenu()).start(Main.stage);
    }

    public void chooseFaction() throws Exception {
        (new ChooseFaction()).start(Main.stage);
    }

    public void chooseLeader() {
        if (Player.getTurn().getPlayerSet().getFaction() != null) {
            try {
                (new ChooseLeader()).start(Main.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select faction first");
            alert.showAndWait();
        }
    }

    public void chooseCards() {
        if (Player.getTurn().getPlayerSet().getLeader() != null) {
            try {
                (new DeckMenu()).start(Main.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select leader first");
            alert.showAndWait();
        }
    }


    public void changeTurn() {
        if (Player.getTurn().getPlayerSet().getRival() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select your rival first");
            alert.showAndWait();
        } else if (Player.getTurn().getPlayerSet().getHand().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select cards first");
            alert.showAndWait();
        } else {
            Player.setTurn(Player.getTurn().getPlayerSet().getRival());
            try {
                (new PreGameMenu()).start(Main.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void chooseRival() {
        String result = controller.setPlayer2(rival.getText());
        Alert alert;
        if (result.equals("player set")) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("player set successfully");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(result);
        }
        alert.showAndWait();
    }

    public void playGame(MouseEvent mouseEvent) {
        PlayerSet firstPlayerSet = Player.getLoggedInUser().getPlayerSet();
        PlayerSet secondPlayerSet = Player.getTurn().getPlayerSet();
        Game game = new Game(firstPlayerSet, secondPlayerSet);
        firstPlayerSet.setGame(game);
        secondPlayerSet.setGame(game);
        (new GameMenu(game)).start(Main.stage);
    }

    public void showInfo(MouseEvent mouseEvent) {
        try {
            (new PregameInfo()).start(Main.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}