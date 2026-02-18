package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;

import java.net.URL;
import java.util.Objects;

public class UsersInfo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = UsersInfo.class.getResource("/FXML/ShowInfo.fxml");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(url));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setLayoutX(180);
        vBox.setLayoutY(110);
        vBox.setStyle("-fx-background-color: #472c2c; -fx-alignment: Center;");

        addText(vBox);
        addButton(vBox);
        pane.getChildren().add(vBox);

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("/CSS.1")));
        stage.setScene(scene);
        stage.show();
    }

    private void addButton(VBox vBox) {
        Button button = new Button();
        button.setText("back");
        button.setOnMouseClicked(event -> {
            backToProfileMenu();
        });
        vBox.getChildren().add(button);
    }

    private void addText(VBox vBox) {
        Text username = new Text();
        username.getStyleClass().add("text-username-style");
        username.setText(Player.getLoggedInUser().getUsername());

        Text nickname = new Text();
        nickname.getStyleClass().add("text-style");
        nickname.setText("Nickname: " + Player.getLoggedInUser().getNickname());

        Text maxScore = new Text();
        maxScore.getStyleClass().add("text-style");
        maxScore.setText("Max score: " + Player.getLoggedInUser().getMaxScore());

        Text rank = new Text();
        rank.getStyleClass().add("text-style");
        rank.setText("Rank: " + Player.getLoggedInUser().getRank());

        Text allGames = new Text();
        allGames.getStyleClass().add("text-style");
        allGames.setText("Number of All games: " + Player.getLoggedInUser().getPlayInfo().getNumberOfAllGames());

        Text wins = new Text();
        wins.getStyleClass().add("text-style");
        wins.setText("Number of wins: " + Player.getLoggedInUser().getPlayInfo().getNumberOfWins());

        Text lost = new Text();
        lost.getStyleClass().add("text-style");
        lost.setText("Number of lost: " + Player.getLoggedInUser().getPlayInfo().getNumberOfLost());

        Text draw = new Text();
        draw.getStyleClass().add("text-style");
        draw.setText("Number of draw: " + Player.getLoggedInUser().getPlayInfo().getNumberOfDraw());


        vBox.getChildren().addAll(username, nickname, maxScore, rank, allGames, wins, lost, draw);
    }

    private void backToProfileMenu() {
        try {
            (new ProfileMenu()).start(Main.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}