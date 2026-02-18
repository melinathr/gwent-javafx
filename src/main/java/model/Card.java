package model;


import controller.GameController;
import enums.UnitCard;
import enums.CardPlacement;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Objects;

public class Card extends Rectangle {
    private UnitCard unitCard;
    private int power;
    private Image fullImage;
    private boolean isFullImageDisplayed = false;
    private CardRow row = null;
    private Rectangle imageWithDescription = new Rectangle();
    private boolean isInDiscardPile = false;
    private GameController gameController = new GameController();
    private boolean isPlaying = false;


    public Card(UnitCard unitCard) {
        initializeCard(unitCard);
    }



    public void initializeCard(UnitCard unitCard) {
        this.unitCard = unitCard;
        this.power = unitCard.getPower();
        initializeFullImage();
    }

    public boolean isFullImageDisplayed() {
        return isFullImageDisplayed;
    }

    public void setFullImageDisplayed(boolean fullImageDisplayed) {
        isFullImageDisplayed = fullImageDisplayed;
    }

    private void initializeFullImage() {
        fullImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(unitCard.getImagePath())));
        int width = (int) fullImage.getWidth();
        int originalHeight = (int) fullImage.getHeight();
        int croppedHeight = originalHeight - 203;

        PixelReader pixelReader = fullImage.getPixelReader();
        WritableImage croppedImage = new WritableImage(pixelReader, 0, 0, width, croppedHeight);

        setWidth(82 / 1.5);
        setHeight(115 / 1.5);
        setFill(new ImagePattern(croppedImage));
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }


    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public void setUnitCard(UnitCard unitCard) {
        this.unitCard = unitCard;
    }

    public Rectangle getImageWithDescription() {
        return imageWithDescription;
    }

    public Image getFullImage() {
        return fullImage;
    }

    public void setInDiscardPile(boolean inDiscardPile) {
        isInDiscardPile = inDiscardPile;
    }

    public UnitCard getUnitCard() {
        return unitCard;
    }

    public CardRow getRow() {
        return row;
    }

    public void setRow(CardRow row) {
        this.row = row;
    }
}