package view;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Game;
import model.Player;
import model.PlayerSet;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GameHistory extends Application {

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

        // TextField for input
        TextField numberOfGamesInput = new TextField();
        numberOfGamesInput.setPromptText("Enter number of games");

        // Button to show game histories
        Button showHistoriesButton = new Button("Show Histories");
        showHistoriesButton.setOnAction(e -> showGameHistories(numberOfGamesInput.getText(), root));

        // Button to go back to the profile menu
        Button goBackButton = new Button("Back");
        goBackButton.setOnAction(e -> goBackToProfileMenu());

        // Add TextField and Buttons to the layout
        root.getChildren().addAll(numberOfGamesInput, showHistoriesButton, goBackButton);

        // Create Scene
        Scene scene = new Scene(root, 600, 800);
        scene.setFill(Color.BLACK);

        // Set the scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Histories");
        primaryStage.show();
    }

    private void showGameHistories(String input, VBox root) {
        try {
            int n = Integer.parseInt(input);
            if (n <= 0) {
                showError(root, "The number should be a positive integer.");
                return;
            }

            List<Game> gameHistories = getRecentGames(n);

            // Clear previous histories
            root.getChildren().removeIf(node -> node instanceof Label);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Display game histories
            for (Game game : gameHistories) {
                PlayerSet firstPlayerSet = game.getFirstPlayerSet();
                PlayerSet secondPlayerSet = game.getSecondPlayerset();
                String rivalUsername = secondPlayerSet.getPlayer().getUsername();
                String date = game.getDate().format(formatter);
                int firstPlayerScore = game.getFirstPlayerSet().getTotalPoints();
                int secondPlayerScore = game.getSecondPlayerset().getTotalPoints();
                String winner = (firstPlayerScore > secondPlayerScore) ? firstPlayerSet.getPlayer().getUsername() : secondPlayerSet.getPlayer().getUsername();

                Label gameLabel = new Label(
                        "Rival's name: " + rivalUsername + "\n" +
                                "Game date: " + date + "\n" +
                                "Scores: " + firstPlayerScore + " - " + secondPlayerScore + "\n" +
                                "Winner: " + winner
                );
                gameLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 5, 0.5, 0, 0);");
                root.getChildren().add(gameLabel);
            }
        } catch (NumberFormatException e) {
            showError(root, "The number should be an integer.");
        } catch (NoGameHistoryException e) {
            showAlert("No Game Histories", e.getMessage());
        }
    }

    private void showError(VBox root, String message) {
        // Clear previous errors
        root.getChildren().removeIf(node -> node instanceof Label && ((Label) node).getText().contains("Error"));

        Label errorLabel = new Label("Error: " + message);
        errorLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #FF0000; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 5, 0.5, 0, 0);");
        root.getChildren().add(errorLabel);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private List<Game> getRecentGames(int n) throws NoGameHistoryException {
        // Get logged in player
        Player loggedInPlayer = Player.getLoggedInUser();

        if (loggedInPlayer == null) {
            throw new NoGameHistoryException("No user is logged in.");
        }

        // Assuming Player class has a method to get the most recent game
        Game recentGame = loggedInPlayer.getPlayerSet().getGame();  // Replace with actual method to get the most recent game

        List<Game> gameHistories = new ArrayList<>();
        if (recentGame != null) {
            gameHistories.add(recentGame);
        } else {
            throw new NoGameHistoryException("You have no games.");
        }

        // Ensure we only return up to n games (although we currently only handle one game in this dummy implementation)
        return gameHistories.subList(Math.max(0, gameHistories.size() - n), gameHistories.size());
    }

    private void goBackToProfileMenu() {
        try {
            new ProfileMenu().start(primaryStage);
        } catch (IOException e) {
            showAlert("Error", "Unable to load the profile menu.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// Custom exception class
class NoGameHistoryException extends Exception {
    public NoGameHistoryException(String message) {
        super(message);
    }
}
