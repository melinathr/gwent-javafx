package model;

import enums.CardPlacement;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class CardRow extends HBox {
    protected CardPlacement cardPlacement;
    protected final double verticalPosition;
    protected final double horizentalPosition;
    protected ArrayList<Card> cards = new ArrayList<>();
    protected Rectangle rectangle = new Rectangle();
    protected final double spacing = 10;


    public CardRow(CardPlacement cardPlacement, double horizentalPosition, double verticalPosition) {
        this.setSpacing(spacing);
        this.cardPlacement = cardPlacement;
        this.verticalPosition = verticalPosition;
        this.horizentalPosition = horizentalPosition;
        this.setLayoutX(this.horizentalPosition);
        this.setLayoutY(this.verticalPosition);

        if (this instanceof Row) {
            rectangle.setHeight(113 / 1.5);
            rectangle.setWidth(807 / 1.5);
            rectangle.setX(this.getLayoutX());
            rectangle.setY(this.getLayoutY());
        } else {
            rectangle.setHeight(136 / 1.5);
            rectangle.setWidth(276 / 1.5);
            rectangle.setX(142 / 1.5);
            rectangle.setY(449 / 1.5);
        }


    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public double getVerticalPosition() {
        return verticalPosition;
    }

    public double getHorizentalPosition() {
        return horizentalPosition;
    }


    public double getNextCardX() {
        if (cards.isEmpty()) {
            return 0; // Starting x-coordinate for the first card, adjust as needed
        } else {
            Card lastCard = cards.getLast();
            return lastCard.getLayoutX() + lastCard.getWidth() + spacing;
        }
    }


    public CardPlacement getCardPlacement() {
        return cardPlacement;
    }

    public void setCardPlacement(CardPlacement cardPlacement) {
        this.cardPlacement = cardPlacement;
    }
}
