package view;


import enums.CardPlacement;
import enums.UnitCard;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Card;
import model.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import controller.DeckManager;

public class DeckMenu extends Application {
    @FXML
    private ListView<String> availableCardsList;

    @FXML
    private ListView<String> selectedCardsList;

    @FXML
    private Button selectButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button finalizeButton;

    @FXML
    private TextField deckNameField;

    private ObservableList<String> availableCards;
    private ObservableList<String> selectedCards;

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = DeckMenu.class.getResource("/FXML/DeckMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(borderPane);
        // Apply the CSS stylesheet
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/DeckMenu.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void initialize() {
        // Initialize card lists
        availableCards = FXCollections.observableArrayList();
        selectedCards = FXCollections.observableArrayList();

        // Add all cards from the AllCards enum to the availableCards list
        for (UnitCard card : UnitCard.values()) {
            if (Player.getTurn().getPlayerSet().getFaction().equals(card.getFaction()) || card.getFaction().equals("Neutral")) {
                String cardInfo = String.format(
                        "%s (Power: %d  Available: %d  %s  Ability: %s %s)",
                        card.getName(),
                        card.getPower(),
                        card.getNumberOfCardsInGame(),
                        card.getPlayingRows().toString(),
                        card.getAbility() != null ? card.getAbility().toString() : "None",
                        card.isHero() ? "Hero" : "not Hero"
                );
                availableCards.add(cardInfo);
            }
        }

        availableCardsList.setItems(availableCards);
        selectedCardsList.setItems(selectedCards);

        // Set button actions
        selectButton.setOnAction(e -> selectCard());
        deleteButton.setOnAction(e -> deleteSelectedCards());
        finalizeButton.setOnAction(e -> {
            try {
                finalizeSelection();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        selectedCardsList.setOnMouseClicked(this::handleSelectedCardClick);
        loadSavedDeck();
    }

    private void loadSavedDeck() {

        List<String> loadedCards = DeckManager.loadLatestDeck();
        selectedCards.setAll(loadedCards);

    }


    private void selectCard() {
        String selectedCardInfo = availableCardsList.getSelectionModel().getSelectedItem();
        if (selectedCardInfo != null) {
            String selectedCardName = extractCardName(selectedCardInfo);
            // Find the corresponding AllCards enum value
            UnitCard cardEnum = findCardByName(selectedCardName);
            if (cardEnum != null) {
                int occurrences = 0;
                for (String card : selectedCards) {
                    if (extractCardName(card).equals(selectedCardName)) {
                        occurrences++;
                    }
                }
                if (occurrences < cardEnum.getNumberOfCardsInGame()) {
                    selectedCards.add(selectedCardName); // Only add the card name
                }
            }
        }
    }

    private String extractCardName(String cardInfo) {
        return cardInfo.split(" \\(Power: ")[0];
    }

    private void handleSelectedCardClick(MouseEvent event) {
        String selectedCard = selectedCardsList.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            selectedCards.remove(selectedCard);
        }
    }

    private UnitCard findCardByName(String name) {
        for (UnitCard card : UnitCard.values()) {
            if (card.getName().equals(name)) {
                return card;
            }
        }
        return null;
    }

    private void deleteSelectedCards() {
        selectedCards.clear();
    }


    private void finalizeSelection() throws Exception {
        int totalCards = selectedCards.size();
        int specialCardCount = 0;

        for (String selectedCardName : selectedCards) {
            // Find the corresponding UnitCard enum
            UnitCard cardEnum = findCardByName(selectedCardName);
            if (cardEnum != null && (cardEnum.getPlayingRows().contains(CardPlacement.SPELL) || cardEnum.getPlayingRows().contains(CardPlacement.WEATHER))) {
                specialCardCount++;
            }
        }

        if (totalCards < 22) {
            showAlert("Deck Finalization Error", "You need to select at least 22 cards.");
            return;
        }

        if (specialCardCount > 10) {
            showAlert("Deck Finalization Error", "You cannot have more than 10 special cards.");
            return;
        }

        String deckName = "latest deck";
        if (!deckName.isEmpty()) {
            DeckManager.saveDeck(deckName, new ArrayList<>(selectedCards));
        }

        for (String selectedCardName : selectedCards) {
            // Find the corresponding UnitCard enum
            UnitCard cardEnum = findCardByName(selectedCardName);
            if (cardEnum != null) {
                Player.getTurn().getPlayerSet().addCardToDeck(new Card(cardEnum));
                Player.getTurn().getPlayerSet().addCardToHand(new Card(cardEnum));
            }
        }
        (new PreGameMenu()).start(Main.stage);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void saveDeck() {
        String deckName = deckNameField.getText().trim();
        if (!deckName.isEmpty()) {
            DeckManager.saveDeck(deckName, new ArrayList<>(selectedCards));
        }
    }

    @FXML
    private void loadDeck() {
        String deckName = deckNameField.getText().trim();
        if (!deckName.isEmpty()) {
            List<String> loadedCards = DeckManager.loadDeck(deckName);
            selectedCards.setAll(loadedCards);
        }
    }

}
