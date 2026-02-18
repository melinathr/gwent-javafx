package controller;

import enums.CardAbility;
import enums.CardPlacement;
import enums.UnitCard;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import view.EndGameMenu;
import view.GameMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController {
    private Pane pane;
    private Game game;

    private Stage stage;


    public void decoyAbility(Card decoy) {
        for (Card card : game.getTurn().getAllCardsPlaying()) {
            selectCard(card, decoy);
        }
    }

    private void selectCard(Card card, Card decoy) {
        card.setOnMouseClicked(event -> addToDeckDecoy(card, decoy));
        card.setOnMouseEntered(event -> {
            card.setTranslateY(-10);  // Move the card up by 10 pixels
            card.setScaleX(1.1);      // Optionally, slightly enlarge the card
            card.setScaleY(1.1);
        });
        card.setOnMouseExited(event -> {
            card.setTranslateY(0);    // Reset the card position
            card.setScaleX(1.0);      // Reset the card size
            card.setScaleY(1.0);
        });
    }

    private void addToDeckDecoy(Card card, Card decoy) {
        game.getTurn().getAllCardsPlaying().remove(card);

        removeCardFromRow(card);
        game.getTurn().getDeck().addCard(card);

        handleClickOnCards(game.getTurn());
        stopEventHandler();

        ArrayList<Card> arrayList = new ArrayList<>();
        arrayList.add(decoy);
        addCardToDiscardPile(arrayList, game.getTurn(), false);
        prepareNextRound(decoy);
    }

    private void stopEventHandler() {
        for (Card card : game.getTurn().getDiscardPile()) {
            card.setOnMouseEntered(null);
            card.setOnMouseClicked(null);
        }
    }

    HBox discardPileHbox;

    public void medicAbility(Card medic) {
        discardPileHbox = new HBox();
        discardPileHbox.setSpacing(10);
        discardPileHbox.setLayoutX(950 / 1.5);
        discardPileHbox.setLayoutY(530 / 1.5);
        pane.getChildren().add(discardPileHbox);
        for (Card card : game.getTurn().getDiscardPile()) {
            discardPileHbox.getChildren().add(card);
            selectDiscardPile(card, medic);
        }
    }

    private void selectDiscardPile(Card card, Card medic) {
        card.setOnMouseClicked(event -> playDiscardedCard(card, medic));
        card.setOnMouseEntered(event -> {
            card.setTranslateY(-10);  // Move the card up by 10 pixels
            card.setScaleX(1.1);      // Optionally, slightly enlarge the card
            card.setScaleY(1.1);
        });
        card.setOnMouseExited(event -> {
            card.setTranslateY(0);    // Reset the card position
            card.setScaleX(1.0);      // Reset the card size
            card.setScaleY(1.0);
        });
    }


    private void playDiscardedCard(Card card, Card medic) {
        CardRow place = getPlace(card);
        addToRow(card, place);

        handleClickOnCards(game.getTurn());
        stopEventHandler();
        pane.getChildren().remove(discardPileHbox);
        prepareNextRound(medic);
    }


    public void moralBoostAbility(Card card) {
        for (Card otherCard : card.getRow().getCards()) {
            if (!otherCard.equals(card)) otherCard.setPower(otherCard.getPower() + 1);
        }
        prepareNextRound(card);
    }


    public void musterAbility(Card card) {
        for (Card otherCard : game.getTurn().getDeck().getCards()) {
            if (!otherCard.equals(card) && otherCard.getUnitCard().equals(card.getUnitCard())) {
                addToRow(otherCard, card.getRow());
            }
        }
        for (Card otherCard : game.getTurn().getHand()) {
            if (!otherCard.equals(card) && otherCard.getUnitCard().equals(card.getUnitCard())) {
                addToRow(otherCard, card.getRow());
            }
        }
        prepareNextRound(card);
    }


    public void tightBond(Card card) {
        int count = 0;
        ArrayList<Card> sameCards = new ArrayList<>();
        for (Card otherCard : card.getRow().getCards()) {
            if (otherCard.getUnitCard().getPower() == card.getUnitCard().getPower() && otherCard.getUnitCard().getAbility().equals(CardAbility.TIGHT_BOND)) {
                count++;
                sameCards.add(otherCard);
            }
        }

        for (Card card1 : sameCards) {
            card1.setPower(count * card1.getPower());
        }
        prepareNextRound(card);
    }

    public void commandersHornAbility(Card card) {
        for (Card card1 : card.getRow().getCards()) {
            card1.setPower(2 * card1.getPower());
        }
        prepareNextRound(card);
    }

    public void scorchAbility(Card card) {
        PlayerSet mySet = game.getTurn();
        PlayerSet rivalSet = mySet.getRivalPlayerSet();

        if (card.getUnitCard().getName().equals("Scorch")) {
            int maxPower = 0;
            ArrayList<Card> maxCards = new ArrayList<>();
            boolean isMostPowerInRivalSet = true;

            for (Card card1 : rivalSet.getAllCardsPlaying()) {
                if (card1.getPower() > maxPower) {
                    maxPower = card1.getPower();
                    maxCards.clear();
                    maxCards.add(card1);
                }
                if (card1.getPower() == maxPower) {
                    maxCards.add(card1);
                }
            }

            for (Card card1 : mySet.getAllCardsPlaying()) {
                if (card1.getPower() > maxPower) {
                    maxPower = card1.getPower();
                    maxCards.clear();
                    maxCards.add(card1);
                }
                if (card1.getPower() == maxPower) {
                    maxCards.add(card1);
                }
                isMostPowerInRivalSet = false;
            }


            if (isMostPowerInRivalSet) {
                addCardToDiscardPile(maxCards, rivalSet, true);
            } else {
                addCardToDiscardPile(maxCards, mySet, true);
            }
            ArrayList<Card> cards = new ArrayList<>();
            cards.add(card);
            addCardToDiscardPile(cards, mySet, false);
        } else {
            Row row = (Row) card.getRow();
            switch (row.getCardPlacement()) {
                case CardPlacement.SIEGE -> findSumScore(rivalSet.getSiege());
                case CardPlacement.RANGED_COMBAT -> findSumScore(rivalSet.getRanged());
                case CardPlacement.CLOSE_COMBAT -> findSumScore(rivalSet.getClose());
            }
        }

        prepareNextRound(card);
    }


    private void findSumScore(Row row) {
        int sum = 0;
        int maxPower = 0;
        ArrayList<Card> maxCards = new ArrayList<>();

        for (Card card : row.getCards()) {
            if (!card.getUnitCard().isHero()) {
                sum += card.getPower();
                if (card.getPower() > maxPower) {
                    maxPower = card.getPower();
                    maxCards.clear();
                    maxCards.add(card);
                }
                if (card.getPower() == maxPower) {
                    maxCards.add(card);
                }
            }
        }
        if (sum >= 10) {
            addCardToDiscardPile(maxCards, game.getTurn().getRivalPlayerSet(), true);
        }
    }


    private void addCardToDiscardPile(ArrayList<Card> cards, PlayerSet playerSet, boolean isScorch) {
        for (Card card : cards) {
            if (isScorch) {
                card.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/Image/anim_scorch.png").toExternalForm())));
                PauseTransition pause = new PauseTransition(Duration.seconds(1));

                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), card);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);

                SequentialTransition sequentialTransition = new SequentialTransition(pause, fadeOut);
                sequentialTransition.setOnFinished(event -> {
                    playerSet.getDiscardPile().add(card);
                    playerSet.getAllCardsPlaying().remove(card);

                    removeCardFromRow(card);
                    card.setInDiscardPile(true);
                    updateScoreForAllRows();
                    game.getTurn().getRivalPlayerSet().setTotalPoints();
                });
                sequentialTransition.play();
            } else {
                playerSet.getDiscardPile().add(card);
                playerSet.getAllCardsPlaying().remove(card);

                if (card.getParent() instanceof Row)
                    removeCardFromRow(card);
                else
                    pane.getChildren().remove(card);
                card.setInDiscardPile(true);
            }
        }
    }

    private void removeCardFromRow(Card card) {
        card.getRow().getCards().remove(card);
        card.getRow().getChildren().remove(card);
    }

    public void mardroemeAbility(Card card) {
        for (Card otherCard : card.getRow().getCards()) {
            if (!otherCard.equals(card) && otherCard.getUnitCard().getAbility().equals(CardAbility.BERSERKER)) {
                transform(otherCard, UnitCard.VILDKAARL, game.getTurn());
            }
        }
    }


    private void transform(Card card, UnitCard unitCard, PlayerSet playerSet) {
        card.initializeCard(unitCard);
    }

    public void addCardsToDeckRandomly(ArrayList<Card> hand, Deck deck) {
        Collections.shuffle(hand);
        deck.setCards(hand.subList(0, 10));
        hand.removeAll(hand.subList(0, 10));
    }

    public void initializeRows() {
        setRows(game.getTurn(), false);
        setRows(game.getTurn().getRivalPlayerSet(), true);
    }

    private void setRows(PlayerSet playerSet, boolean isRival) {
        playerSet.setSiege(new Row(CardPlacement.SIEGE, 713 / 1.5, isRival ? CardPlacement.SIEGE.getRivalpostition() : CardPlacement.SIEGE.getMyVerticalPosition()));
        playerSet.setRanged(new Row(CardPlacement.RANGED_COMBAT, 713 / 1.5, isRival ? CardPlacement.RANGED_COMBAT.getRivalpostition() : CardPlacement.RANGED_COMBAT.getMyVerticalPosition()));
        playerSet.setClose(new Row(CardPlacement.CLOSE_COMBAT, 713 / 1.5, isRival ? CardPlacement.CLOSE_COMBAT.getRivalpostition() : CardPlacement.CLOSE_COMBAT.getMyVerticalPosition()));
        pane.getChildren().add(playerSet.getSiege());
        pane.getChildren().add(playerSet.getClose());
        pane.getChildren().add(playerSet.getRanged());
        initializeRowScore(playerSet.getSiege());
        initializeRowScore(playerSet.getClose());
        initializeRowScore(playerSet.getRanged());
    }

    private void initializeRowScore(Row row) {
        Label rowPoints = row.getRowPoints();
        rowPoints.setLayoutX(525 / 1.5);
        rowPoints.setLayoutY(row.getLayoutY() + 20);
        rowPoints.setText("0");
        rowPoints.setStyle("-fx-font-size: 20px;");
        pane.getChildren().add(rowPoints);
    }

    public void initializeDeck() {
        setDeck(game.getFirstPlayerSet());
        setDeck(game.getSecondPlayerset());
        pane.getChildren().add(game.getFirstPlayerSet().getDeck());
    }

    private void setDeck(PlayerSet playerSet) {
        Deck deck = new Deck();
        playerSet.setDeck(deck);
        addCardsToDeckRandomly(playerSet.getHand(), deck);
        deck.setLayoutX(Deck.getHorizentalPosition());
        deck.setLayoutY(Deck.getVerticalPosition());
        handleClickOnCards(playerSet);
    }

    private void handleClickOnCards(PlayerSet playerSet) {
        for (Card card : playerSet.getDeck().getCards()) {
            card.setOnMouseClicked(event -> displayFullImage(card));
            card.setOnMouseEntered(event -> {
                card.setTranslateY(-10);  // Move the card up by 10 pixels
                card.setScaleX(1.1);      // Optionally, slightly enlarge the card
                card.setScaleY(1.1);
            });
            card.setOnMouseExited(event -> {
                card.setTranslateY(0);    // Reset the card position
                card.setScaleX(1.0);      // Reset the card size
                card.setScaleY(1.0);
            });
        }
    }


    private void displayFullImage(Card card) {
        Rectangle imageWithDescription = card.getImageWithDescription();
        imageWithDescription.setWidth(card.getFullImage().getWidth() / 2);
        imageWithDescription.setHeight(card.getFullImage().getHeight() / 2);
        imageWithDescription.setFill(new ImagePattern(card.getFullImage()));

        imageWithDescription.setX(1540 / 1.5);
        imageWithDescription.setY(227 / 1.5);

        card.setOnMouseClicked(null);
        card.setOnMouseEntered(null);
        game.setSelectedCard(card);
        pane.getChildren().add(imageWithDescription);
        setRowsCardHalo(card);
    }

    private void setRowsCardHalo(Card card) {
        List<CardPlacement> cardPlacements = card.getUnitCard().getPlayingRows();
        CardRow place;

        if (cardPlacements.contains(CardPlacement.SPELL)) {
            checkAbility(card);
            return;
        } else {
            place = getPlace(card);
        }

        Rectangle rectangle = place.getRectangle();

        if (!pane.getChildren().contains(rectangle)) pane.getChildren().add(rectangle);
        rectangle.getStyleClass().add("row-halo");

        rectangle.setOnMouseEntered(e -> rectangle.getStyleClass().add("row-outline"));
        rectangle.setOnMouseExited(e -> rectangle.getStyleClass().remove("row-outline"));
        rectangle.setOnMouseClicked(mouseEvent -> {
            addCardToRowAnimation(place, card);
            clearSelection(place);
        });

    }

    private CardRow getPlace(Card card) {
        PlayerSet playerSet;
        List<CardPlacement> cardPlacements = card.getUnitCard().getPlayingRows();
        if (card.getUnitCard().getAbility() != null && card.getUnitCard().getAbility().equals(CardAbility.SPY)) {
            playerSet = game.getTurn().getRivalPlayerSet();
        } else playerSet = game.getTurn();

        if (cardPlacements.contains(CardPlacement.SIEGE)) {
            return playerSet.getSiege();
        } else if (cardPlacements.contains(CardPlacement.CLOSE_COMBAT)) {
            return playerSet.getClose();
        } else if (cardPlacements.contains(CardPlacement.RANGED_COMBAT)) {
            return playerSet.getRanged();
        } else if (cardPlacements.contains(CardPlacement.WEATHER)) {
            return game.getWeather();
        } else return null;
    }


    private void prepareNextRound(Card card) {
        pane.getChildren().remove(card.getImageWithDescription());
        card.setFullImageDisplayed(false);
        game.setSelectedCard(null);
        removeFromDeck(game.getTurn().getDeck(), card);
        // Reset click handlers for all cards in the deck
        handleClickOnCards(game.getTurn());
        card.setOnMouseEntered(null);
        card.setOnMouseClicked(null);
        // Perform the rest of the actions

        updateScoreForAllRows();
        // TODO update score for all the rows in fact!

        game.getTurn().setTotalPoints();
        game.getTurn().getRivalPlayerSet().setTotalPoints();

        if (!game.getTurn().getRivalPlayerSet().isPassedRound()) {
            showRoundPassingImage();
        }
    }

    private void addCardToRowAnimation(CardRow row, Card card) {
        double startX = card.getLayoutX() + Deck.getHorizentalPosition();
        double startY = Deck.getVerticalPosition();
        double endX;
        if (card.getUnitCard().getAbility() != null && card.getUnitCard().getAbility().equals(CardAbility.COMMANDERS_HORN))
            endX = 587 / 1.5;
        else endX = row.getNextCardX() + row.getHorizentalPosition();
        double endY = row.getLayoutY();

        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), card);
        transition.setToX(endX - startX);
        transition.setToY(endY - startY);

        // Set up the onFinished event handler
        transition.setOnFinished(event -> {
            card.setLayoutX(endX);
            card.setLayoutY(endY);

            card.setTranslateX(0);
            card.setTranslateY(0);


            addToRow(card, row);
            checkAbility(card);
        });

        // Start the animation
        transition.play();
    }


    private void addToRow(Card card, CardRow row) {
        if (card.getUnitCard().getAbility() != null && card.getUnitCard().getAbility().equals(CardAbility.COMMANDERS_HORN)) {
            ((Row) row).setCommandersHorn(card);
            pane.getChildren().add(card);
        } else {
            row.getChildren().add(card);
            row.getCards().add(card);
        }
        game.getTurn().getAllCardsPlaying().add(card);
        card.setRow(row);
        if (row instanceof Weather) applyWeather(card);
    }


    private void applyWeather(Card card) {
        switch (card.getUnitCard()) {
            case BITING_FROST -> frostEffect();
            case IMPENETRABLE_FOG -> fogEffect();
            case TORRENTIAL_RAIN -> rainEffect();
            case SKELLIGE_STORM -> stormEffect();
            case CLEAR_WEATHER -> clearWeatherEffect();
        }
    }

    private void clearWeatherEffect() {
        Weather weather = game.getWeather();
        ArrayList<Card> weatherCards = new ArrayList<>(weather.getCards());
        for (Card card : weatherCards) {
            if (!card.getUnitCard().equals(UnitCard.CLEAR_WEATHER)) {
                weather.getCards().remove(card);
                weather.getChildren().remove(card);
            }
        }
        cancelWeatherForPlayerSet(game.getTurn());
        cancelWeatherForPlayerSet(game.getTurn().getRivalPlayerSet());
    }

    private void cancelWeatherForPlayerSet(PlayerSet playerSet) {
        cancelWeather(playerSet.getRanged());
        cancelWeather(playerSet.getSiege());
        cancelWeather(playerSet.getClose());
    }


    private void cancelWeather(Row row) {
        row.setHasWeatherEffect(false);
        Rectangle rectangle = row.getRectangle();
        rectangle.setFill(null);
        pane.getChildren().remove(rectangle);

        for (Card card : row.getCards()) {
            card.setPower(card.getUnitCard().getPower());
        }
    }

    private void stormEffect() {
        rainEffect();
        fogEffect();
    }

    private void rainEffect() {
        weatherEffect(game.getTurn().getSiege(), "overlay_rain");
        weatherEffect(game.getTurn().getRivalPlayerSet().getSiege(), "overlay_rain");
    }

    private void fogEffect() {
        weatherEffect(game.getTurn().getRanged(), "overlay_fog");
        weatherEffect(game.getTurn().getRivalPlayerSet().getRanged(), "overlay_fog");
    }

    private void frostEffect() {
        weatherEffect(game.getTurn().getClose(), "overlay_frost");
        weatherEffect(game.getTurn().getRivalPlayerSet().getClose(), "overlay_frost");
    }


    private void weatherEffect(Row row, String name) {
        row.setHasWeatherEffect(true);
        Rectangle rectangle = row.getRectangle();
        rectangle.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/Image/" + name + ".png").toExternalForm())));
        pane.getChildren().add(rectangle);

        for (Card card : row.getCards()) {
            if (!card.getUnitCard().isHero()) {
                card.setPower(1);
            }
        }
    }

    private void checkAbility(Card card) {
        if (card.getUnitCard().getAbility() != null) {
            switch (card.getUnitCard().getAbility()) {

                case CardAbility.DECOY -> decoyAbility(card);
                case CardAbility.MEDIC -> medicAbility(card);
                case CardAbility.MORALE_BOOST -> moralBoostAbility(card);
                case CardAbility.MUSTER -> musterAbility(card);
                case CardAbility.SPY -> spyAbility();
                case CardAbility.TIGHT_BOND -> tightBond(card);
                case CardAbility.COMMANDERS_HORN -> commandersHornAbility(card);
                case CardAbility.SCORCH -> scorchAbility(card);
                case CardAbility.MARDROEME -> mardroemeAbility(card);
            }
        } else {
            prepareNextRound(card);
        }
    }

    private void spyAbility() {
        ArrayList<Card> hand = game.getTurn().getHand();
        Collections.shuffle(hand);
        addToDeck(hand.subList(0, 2));
    }

    private void addToDeck(List<Card> cards) {
        for (Card card : cards) {
            game.getTurn().getDeck().addCard(card);
            game.getTurn().getHand().remove(card);
        }
    }


    private void showRoundPassingImage() {
        ImageView roundPassingImageView = new ImageView(GameMenu.class.getResource("/Image/notif_op_turn.png").toExternalForm());
        pane.getChildren().add(roundPassingImageView);
        roundPassingImageView.setOpacity(0);
        roundPassingImageView.setVisible(true);

        roundPassingImageView.setX(600 / 1.5);
        roundPassingImageView.setY(300 / 1.5);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), roundPassingImageView);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), roundPassingImageView);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        SequentialTransition sequentialTransition = new SequentialTransition(fadeIn, pause, fadeOut);
        sequentialTransition.setOnFinished(event -> {
            roundPassingImageView.setVisible(false);
            changeTurn(game.getTurn(), game.getTurn().getRivalPlayerSet());
        });

        sequentialTransition.play();
    }

    public void clearSelection(CardRow row) {
        Card card = game.getSelectedCard();
        if (card != null) {
            pane.getChildren().remove(card.getImageWithDescription());
            card.setFullImageDisplayed(false);
            game.setSelectedCard(null);
            row.getRectangle().getStyleClass().remove("row-halo");
            row.getRectangle().getStyleClass().remove("row-outline");
            row.getRectangle().setOnMouseClicked(null);
            row.getRectangle().setOnMouseEntered(null);

            if (!(row instanceof Row && ((Row) row).isHasWeatherEffect()))
                pane.getChildren().remove(row.getRectangle());
        }

        // Reset click handlers for all cards in the deck
        handleClickOnCards(game.getTurn());
    }

    private void removeFromDeck(Deck deck, Card card) {
        deck.getCards().remove(card);
        deck.getChildren().remove(card);
    }

    private void updateScoreForAllRows() {
        updateRowScore(game.getTurn());
        updateRowScore(game.getTurn().getRivalPlayerSet());
    }

    private void updateRowScore(PlayerSet playerSet) {
        getRowScore(playerSet.getSiege());
        getRowScore(playerSet.getRanged());
        getRowScore(playerSet.getClose());
    }

    private void getRowScore(Row row) {
        int points = 0;
        for (Card card : row.getCards()) {
            points += card.getPower();
        }
        if (row.getCommandersHorn() != null)
            points += row.getCommandersHorn().getPower();
        row.setPoints(points);
        row.getRowPoints().setText(String.valueOf(points));
    }

    public void initializeWeather() {
        Weather weather = new Weather(CardPlacement.WEATHER, 147 / 1.5, 461 / 1.5);
        game.setWeather(weather);
        pane.getChildren().add(weather);
        weather.setLayoutX(weather.getHorizentalPosition());
        weather.setLayoutY(weather.getVerticalPosition());
    }


    public void initializeBoard(Stage stage, Pane pane, Game game) {
        this.game = game;
        this.pane = pane;
        this.stage = stage;
        initializeRows();
        initializeDeck();
        initializeWeather();
        initializeTotalScore();
        initializeGems();
    }

    private void initializeGems() {
        setGems(game.getTurn().getGem1(), 303 / 1.5, 343 / 1.5);
        setGems(game.getTurn().getGem2(), 355 / 1.5, 343 / 1.5);
        setGems(game.getTurn().getRivalPlayerSet().getGem1(), 303 / 1.5, 650 / 1.5);
        setGems(game.getTurn().getRivalPlayerSet().getGem2(), 355 / 1.5, 650 / 1.5);
    }


    private void setGems(Rectangle gem, double x, double y) {
        Image gemImage = new Image(GameMenu.class.getResource("/Image/icon_gem_on.png").toExternalForm());
        if (gemImage.isError()) {
            System.out.println("Error loading gem image.");
        } else {
            gem.setFill(new ImagePattern(gemImage));
            gem.setWidth(30); // Set a default width
            gem.setHeight(30); // Set a default height
            gem.setX(x);
            gem.setY(y);
            pane.getChildren().add(gem);
        }
    }


    private void initializeTotalScore() {
        game.getFirstPlayerSet().setTotalPoints(0);
        game.getSecondPlayerset().setTotalPoints(0);
        setTotalScore(game.getFirstPlayerSet(), 700 / 1.5);
        setTotalScore(game.getSecondPlayerset(), 300 / 1.5);
    }

    private void setTotalScore(PlayerSet playerSet, double y) {
        Label label = playerSet.getTotalPointsLabel();
        label.setLayoutY(y);
        label.setLayoutX(440 / 1.5);
        label.setText("0");
        label.setStyle("-fx-font-size: 25px;");
        pane.getChildren().add(label);
    }

    public void passRound() {
        PlayerSet mySet = game.getTurn();
        PlayerSet rivalSet = game.getTurn().getRivalPlayerSet();
        if (rivalSet.isPassedRound()) {
            endRound();
        } else {
            mySet.setPassedRound(true);
            changeTurn(mySet, rivalSet);
        }
    }

    public void changeTurn(PlayerSet mySet, PlayerSet rivalSet) {
        changeRows(mySet, true);
        changeRows(rivalSet, false);
        setRivalDeck(mySet, rivalSet);
        changeTotalPoints(mySet, rivalSet);

        game.changeTurn();
    }


    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void endRound() {
        PlayerSet mySet = game.getTurn();
        PlayerSet rivalSet = game.getTurn().getRivalPlayerSet();
        mySet.setPassedRound(false);
        rivalSet.setPassedRound(false);
        saveRoundPoints(mySet);
        saveRoundPoints(rivalSet);


        if (mySet.getTotalPoints() > rivalSet.getTotalPoints()) {
            if (rivalSet.getLives() != 1) {
                rivalSet.setLives(rivalSet.getLives() - 1);
                removeGem(rivalSet);
                startNewRound(true);
            } else {
                endGame(mySet);
            }
        } else if (mySet.getTotalPoints() < rivalSet.getTotalPoints()) {
            if (mySet.getLives() != 1) {
                mySet.setLives(mySet.getLives() - 1);
                removeGem(mySet);
                startNewRound(false);
            } else {
                endGame(rivalSet);
            }
        } else {
            rivalSet.setLives(rivalSet.getLives() - 1);
            mySet.setLives(mySet.getLives() - 1);
            removeGem(mySet);
            removeGem(rivalSet);
        }
    }

    private void endGame(PlayerSet winnerSet) {
        if (winnerSet.equals(game.getFirstPlayerSet())) (new EndGameMenu(game, true)).start(stage);
        else (new EndGameMenu(game, false)).start(stage);
    }

    private void removeGem(PlayerSet playerSet) {
        Rectangle gem2 = playerSet.getGem2();
        gem2.setFill(new ImagePattern(new Image(GameMenu.class.getResource("/Image/icon_gem_off.png").toExternalForm())));
    }

    private void startNewRound(boolean changeTurn) {
        if (changeTurn) {
            changeGems(game.getTurn(), true);
            changeGems(game.getTurn().getRivalPlayerSet(), false);
            setRivalDeck(game.getTurn(), game.getTurn().getRivalPlayerSet());
            game.changeTurn();
        }

        removeRows();
        removeRowScore();
        removeTotalScores();
        initializeRows();
        initializeWeather();
        initializeTotalScore();
    }

    private void removeTotalScores() {
        PlayerSet mySet = game.getTurn();
        PlayerSet rivalSet = game.getTurn().getRivalPlayerSet();
        pane.getChildren().remove(mySet.getTotalPointsLabel());
        pane.getChildren().remove(rivalSet.getTotalPointsLabel());
    }

    private void removeRowScore() {
        PlayerSet mySet = game.getTurn();
        PlayerSet rivalSet = game.getTurn().getRivalPlayerSet();
        pane.getChildren().remove(mySet.getSiege().getRowPoints());
        pane.getChildren().remove(mySet.getRanged().getRowPoints());
        pane.getChildren().remove(mySet.getClose().getRowPoints());
        pane.getChildren().remove(rivalSet.getSiege().getRowPoints());
        pane.getChildren().remove(rivalSet.getRanged().getRowPoints());
        pane.getChildren().remove(rivalSet.getClose().getRowPoints());
    }

    private void removeRows() {
        PlayerSet mySet = game.getTurn();
        PlayerSet rivalSet = game.getTurn().getRivalPlayerSet();
        pane.getChildren().remove(mySet.getSiege());
        pane.getChildren().remove(mySet.getRanged());
        pane.getChildren().remove(mySet.getClose());
        pane.getChildren().remove(rivalSet.getSiege());
        pane.getChildren().remove(rivalSet.getClose());
        pane.getChildren().remove(rivalSet.getRanged());
    }


    private void saveRoundPoints(PlayerSet playerSet) {
        playerSet.getRoundsScore().add(playerSet.getTotalPoints());
    }

    private void changeTotalPoints(PlayerSet mySet, PlayerSet rivalSet) {
        mySet.getTotalPointsLabel().setLayoutY(300 / 1.5);
        rivalSet.getTotalPointsLabel().setLayoutY(700 / 1.5);
    }

    private void setRivalDeck(PlayerSet mySet, PlayerSet rivalSet) {
        pane.getChildren().add(rivalSet.getDeck());
        pane.getChildren().remove(mySet.getDeck());
    }

    private void changeRows(PlayerSet playerSet, boolean changeToRival) {
        Row siege = playerSet.getSiege();
        Row close = playerSet.getClose();
        Row ranged = playerSet.getRanged();
        siege.setLayoutY(changeToRival ? CardPlacement.SIEGE.getRivalpostition() : CardPlacement.SIEGE.getMyVerticalPosition());
        close.setLayoutY(changeToRival ? CardPlacement.CLOSE_COMBAT.getRivalpostition() : CardPlacement.CLOSE_COMBAT.getMyVerticalPosition());
        ranged.setLayoutY(changeToRival ? CardPlacement.RANGED_COMBAT.getRivalpostition() : CardPlacement.RANGED_COMBAT.getMyVerticalPosition());

        siege.getRowPoints().setLayoutY(siege.getLayoutY() + 20);
        close.getRowPoints().setLayoutY(close.getLayoutY() + 20);
        ranged.getRowPoints().setLayoutY(ranged.getLayoutY() + 20);

        siege.getRectangle().setY(siege.getLayoutY());
        close.getRectangle().setY(close.getLayoutY());
        ranged.getRectangle().setY(ranged.getLayoutY());

        if (siege.getCommandersHorn() != null) siege.getCommandersHorn().setLayoutY(siege.getLayoutY());
        if (close.getCommandersHorn() != null) close.getCommandersHorn().setLayoutY(close.getLayoutY());
        if (ranged.getCommandersHorn() != null) ranged.getCommandersHorn().setLayoutY(ranged.getLayoutY());

        changeGems(playerSet, changeToRival);
    }


    private void changeGems(PlayerSet playerSet, boolean changeToRival) {
        playerSet.getGem1().setY(changeToRival ? 343 / 1.5 : 650 / 1.5);
        playerSet.getGem2().setY(changeToRival ? 343 / 1.5 : 650 / 1.5);
    }

}
