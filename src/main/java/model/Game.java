package model;

import java.util.HashMap;
import java.time.LocalDateTime;

public class Game {
    private final PlayerSet firstPlayerSet;
    private final PlayerSet secondPlayerset;
    private Weather weather;
    private Card selectedCard = null;
    private PlayerSet turn;
    private final LocalDateTime date;


    public Game(PlayerSet firstPlayerSet, PlayerSet secondPlayerset) {
        this.firstPlayerSet = firstPlayerSet;
        this.secondPlayerset = secondPlayerset;
        turn = firstPlayerSet;
        this.date = LocalDateTime.now();
    }

    public Card getSelectedCard() {
        return selectedCard;
    }


    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }


    public PlayerSet getTurn() {
        return turn;
    }

    public void changeTurn() {
        turn = turn.getRivalPlayerSet();
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public PlayerSet getFirstPlayerSet() {
        return firstPlayerSet;
    }

    public PlayerSet getSecondPlayerset() {
        return secondPlayerset;
    }
    public LocalDateTime getDate() {
        return date;
    }

}


