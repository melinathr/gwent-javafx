package model;

import controller.GameController;
import enums.CardPlacement;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Deck extends HBox {
    private GameController gameController = new GameController();
    private ArrayList<Card> cards = new ArrayList<>();
    private final double spacing = 5;
    private static final double verticalPosition = 850 / 1.5;
    private static final double horizentalPosition = 580 / 1.5;



    public Deck() {
        this.setSpacing(spacing);
    }

    public void setCards(List<Card> cards) {
        for (Card card : cards)
            addCard(card);
    }

    public void addCard(Card card) {
        cards.add(card);
        this.getChildren().add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }


    public static double getHorizentalPosition() {
        return horizentalPosition;
    }

    public static double getVerticalPosition() {
        return verticalPosition;
    }



    public int getNumberOfSpecialCardsInDeck() {
        // Filter the cards list to get only those cards that have a special card placement
        long count = cards.stream()
                .filter(card -> {
                    List<CardPlacement> placements = card.getUnitCard().getPlayingRows();
                    return placements.contains(CardPlacement.SPECIAL)
                            || placements.contains(CardPlacement.SPECIAL_SLOT)
                            || placements.contains(CardPlacement.SPELL)
                            || placements.contains(CardPlacement.WEATHER);
                })
                .count();

        return (int) count;
    }


    public int getNumberOfHeroCardsInDeck() {
        // Filter the cards list to count cards that are hero cards
        long count = cards.stream()
                .filter(card -> card.getUnitCard().isHero())
                .count();

        return (int) count;
    }

}
