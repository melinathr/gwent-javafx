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


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.PlayerSet;

import java.net.URL;
import java.util.Objects;

public class PregameInfo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Player loggedInUser = Player.getLoggedInUser();
        PlayerSet playerSet = loggedInUser.getPlayerSet();

        URL url = PregameInfo.class.getResource("/FXML/ShowInfo.fxml");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(url));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setLayoutX(180);
        vBox.setLayoutY(110);
        vBox.setStyle("-fx-background-color: #472c2c; -fx-alignment: Center;");

        addText(loggedInUser, playerSet, vBox);
        addButton(vBox);
        pane.getChildren().add(vBox);

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("/CSS.1")));
        stage.setScene(scene);
        stage.show();
    }

    private void addButton(VBox vBox) {
        Button button = new Button();
        button.setText("Back");
        button.setOnMouseClicked(event -> {
            backToProfileMenu();
        });
        vBox.getChildren().add(button);
    }

    private void addText(Player loggedInUser, PlayerSet playerSet, VBox vBox) {
        Text username = new Text();
        username.getStyleClass().add("text-username-style");
        username.setText(loggedInUser.getUsername());

        Text factionName = new Text();
        factionName.getStyleClass().add("text-style");
        factionName.setText("Faction Name: " + (playerSet.getFaction() != null ? playerSet.getFaction() : "Unknown"));

        int allCardsInDeckCount = playerSet.getDeck() != null ? playerSet.getDeck().getCards().size() : 0;
        Text allCardsInDeck = new Text();
        allCardsInDeck.getStyleClass().add("text-style");
        allCardsInDeck.setText("All Cards in Deck: " + allCardsInDeckCount);

        int allCardsInHandCount = playerSet.getHand() != null ? playerSet.getHand().size() : 0;
        Text allCardsInHand = new Text();
        allCardsInHand.getStyleClass().add("text-style");
        allCardsInHand.setText("All Cards in Hand: " + allCardsInHandCount);

        int specialCardsInDeckCount = playerSet.getDeck() != null ? playerSet.getDeck().getNumberOfSpecialCardsInDeck() : 0;
        Text specialCardsInDeck = new Text();
        specialCardsInDeck.getStyleClass().add("text-style");
        specialCardsInDeck.setText("Special Cards in Deck: " + specialCardsInDeckCount);

        int heroCardsInDeckCount = playerSet.getDeck() != null ? playerSet.getDeck().getNumberOfHeroCardsInDeck() : 0;
        Text heroCardsInDeck = new Text();
        heroCardsInDeck.getStyleClass().add("text-style");
        heroCardsInDeck.setText("Hero Cards in Deck: " + heroCardsInDeckCount);

        int notSpecialCardsInDeckCount = allCardsInDeckCount - specialCardsInDeckCount;
        Text notSpecialCardsInDeck = new Text();
        notSpecialCardsInDeck.getStyleClass().add("text-style");
        notSpecialCardsInDeck.setText("Soldier Cards in Deck: " + notSpecialCardsInDeckCount);

        vBox.getChildren().addAll(username, factionName, allCardsInDeck, allCardsInHand, specialCardsInDeck,
                heroCardsInDeck, notSpecialCardsInDeck);
    }

    private void backToProfileMenu() {
        try {
            (new PreGameMenu()).start(Main.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
