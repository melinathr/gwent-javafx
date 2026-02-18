package model;

import enums.CardPlacement;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Row extends CardRow {
    private Card commandersHorn;
    private int points = 0;
    private Label rowPoints = new Label();
    private boolean hasWeatherEffect = false;

    public Row(CardPlacement cardPlacement, double horizontalPosition, double verticalPosition) {
        super(cardPlacement, horizontalPosition, verticalPosition);
    }


    public Label getRowPoints() {
        return rowPoints;
    }

    public void setRowPoints(Label rowPoints) {
        this.rowPoints = rowPoints;
    }

    public boolean isHasWeatherEffect() {
        return hasWeatherEffect;
    }

    public void setHasWeatherEffect(boolean hasWeatherEffect) {
        this.hasWeatherEffect = hasWeatherEffect;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Card getCommandersHorn() {
        return commandersHorn;
    }

    public void setCommandersHorn(Card commandersHorn) {
        this.commandersHorn = commandersHorn;
    }
}