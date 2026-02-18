
package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Player;

import java.util.Comparator;
import java.util.List;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Player;

import java.util.Comparator;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Player;

import java.util.Comparator;
import java.util.List;

public class RankingMenu extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Create VBox for layout
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setAlignment(Pos.CENTER);

        // Get ranked players
        List<Player> rankedPlayers = getRankedPlayers();

        // Display players' ranks, usernames, and max scores
        for (int i = 0; i < rankedPlayers.size(); i++) {
            Player player = rankedPlayers.get(i);
            int rank = i + 1;
            String playerName = player.getUsername();
            int maxScore = player.getMaxScore();
            Label playerLabel = new Label("rank "+ rank + ": " + playerName + " (" + maxScore + ")");
            playerLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 5, 0.5, 0, 0);");
            root.getChildren().add(playerLabel);
        }

        // Back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 16px; -fx-text-fill: #FFFFFF; -fx-background-color: linear-gradient(#3b0505, #603c3c); -fx-background-radius: 5; -fx-padding: 10 20; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 5, 0.5, 0, 0); -fx-cursor: hand; -fx-border-color: #FFFFFF; -fx-border-width: 2; -fx-border-radius: 5; -fx-pref-width: 200px;");
        backButton.setOnAction(e -> goBack());
        root.getChildren().add(backButton);

        // Create Scene
        Scene scene = new Scene(root, 600, 800);
        scene.setFill(Color.BLACK);

        // Set background image
        String imagePath = getClass().getResource("/Image/nebula.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + imagePath + "'); -fx-background-repeat: no-repeat; -fx-background-size: cover; -fx-background-position: center center;");

        // Set the scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ranked Players");
        primaryStage.show();
    }

    private void goBack() {
        // Logic to navigate back to the desired menu, e.g., main menu
        try {
            new MainMenu().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private List<Player> getRankedPlayers() {
        // Use Player.getAllUsers() to get the list of all players
        List<Player> players = Player.getAllUsers();

        // Sort players by max points in descending order
        players.sort(Comparator.comparingInt(Player::getMaxScore).reversed());

        return players;
    }
}

