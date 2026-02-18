package model;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Spell extends HBox {
    private ArrayList<Card> cards = new ArrayList<>();
    private final double verticalPosition = 461 / 1.5;
    private final double horizentalPosition = 147 / 1.5;

    public double getVerticalPosition() {
        return verticalPosition;
    }

    public double getHorizentalPosition() {
        return horizentalPosition;
    }
}
