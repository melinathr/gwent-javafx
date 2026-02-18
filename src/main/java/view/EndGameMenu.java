package view;

import controller.GameController;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;
import model.PlayerSet;


public class EndGameMenu extends Application {
    private Game game;
    private ImageView endGameImage;

    public EndGameMenu(Game game, boolean win) {
        this.game = game;
        endGameImage = new ImageView(GameMenu.class.getResource(win ? "/Image/end_win.png" : "/Image/end_lose.png").toExternalForm());
    }

    @Override
    public void start(Stage stage) {
        // Close the previous stage if it's open
        Stage previousStage = (Stage) stage.getScene().getWindow();
        previousStage.close();

        // Create a new stage for the end game menu
        Stage endGameStage = new Stage();
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: black;");  // Set the background color to black

        // Set up the image
        endGameImage.setPreserveRatio(true);
        endGameImage.setFitWidth(400);  // Adjust the size as needed

        // Create the TableView
        TableView<RoundPoints> tableView = createPointsTable();

        // Center the image and table in the scene
        double sceneWidth = 800;
        double sceneHeight = 600;
        double imageX = (sceneWidth - endGameImage.getFitWidth()) / 2;
        double imageY = (sceneHeight - endGameImage.getFitHeight() - tableView.getPrefHeight()) / 8;
        double tableX = (sceneWidth - tableView.getPrefWidth()) / 2;
        double tableY = imageY + endGameImage.getFitHeight() + 300;

        endGameImage.setX(imageX);
        endGameImage.setY(imageY);

        tableView.setLayoutX(tableX);
        tableView.setLayoutY(tableY);

        pane.getChildren().addAll(endGameImage, tableView);

        Scene scene = new Scene(pane, sceneWidth, sceneHeight);  // Set the scene size as needed
        scene.getStylesheets().add(GameMenu.class.getResource("/CSS/1.css").toExternalForm());

        endGameStage.setScene(scene);
        endGameStage.setResizable(false);
        endGameStage.centerOnScreen();
        endGameStage.show();
    }

    private TableView<RoundPoints> createPointsTable() {
        TableView<RoundPoints> tableView = new TableView<>();


        TableColumn<RoundPoints, String> roundColumn = new TableColumn<>("Round");
        roundColumn.setCellValueFactory(new PropertyValueFactory<>("round"));

        TableColumn<RoundPoints, Integer> firstPlayerColumn = new TableColumn<>("First Player");
        firstPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("firstPlayerPoints"));

        TableColumn<RoundPoints, Integer> secondPlayerColumn = new TableColumn<>("Second Player");
        secondPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("secondPlayerPoints"));

        tableView.getColumns().addAll(roundColumn, firstPlayerColumn, secondPlayerColumn);


        for (int i = 0; i < 3; i++) {
            PlayerSet mySet = game.getTurn();
            PlayerSet rivalSet = mySet.getRivalPlayerSet();
            int firstPlayerPoints;
            int secondPlayerPoints;
            if (mySet.equals(game.getFirstPlayerSet())) {
                firstPlayerPoints = mySet.getRoundsScore().get(i);
                secondPlayerPoints = rivalSet.getRoundsScore().get(i);

            } else {
                firstPlayerPoints = rivalSet.getRoundsScore().get(i);
                secondPlayerPoints = mySet.getRoundsScore().get(i);
            }
            tableView.getItems().add(new RoundPoints("Round " + (i+1), firstPlayerPoints, secondPlayerPoints));

        }
        // Limit the height to fit only 3 rows
        tableView.setPrefHeight(300); // Adjust this value based on row height (assuming 24px per row and 26px for the header)
        tableView.setPrefWidth(300); // Set the width of the table

        return tableView;
    }

    public static class RoundPoints {
        private String round;
        private int firstPlayerPoints;
        private int secondPlayerPoints;

        public RoundPoints(String round, int firstPlayerPoints, int secondPlayerPoints) {
            this.round = round;
            this.firstPlayerPoints = firstPlayerPoints;
            this.secondPlayerPoints = secondPlayerPoints;
        }

        public String getRound() {
            return round;
        }

        public int getFirstPlayerPoints() {
            return firstPlayerPoints;
        }

        public int getSecondPlayerPoints() {
            return secondPlayerPoints;
        }
    }
}