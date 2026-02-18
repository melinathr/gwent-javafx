package model;

import controller.GameController;
import enums.CardAbility;
import enums.CardPlacement;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class PlayerSet {
    private Player player;
    private Player rival;
    private Row siege;
    private Row ranged;
    private Row close;
    private Deck deck = new Deck() ;
    private ArrayList<Card> hand = new ArrayList<>();
    private String faction = null;
    private String leader = null;
    private Game game;
    private int lives = 2;
    private ArrayList<Card> allCardsPlaying = new ArrayList<>();
    private ArrayList<Card> discardPile = new ArrayList<>();
    private GameController gameController = new GameController();
    private int totalPoints = 0;
    private Label totalPointsLabel = new Label();
    private PlayerSet rivalPlayerSet;
    private boolean passedRound = false;
    private Rectangle gem1 = new Rectangle();
    private Rectangle gem2 =  new Rectangle();
    private ArrayList<Integer> roundsScore = new ArrayList<>();


    public PlayerSet(Player player) {
        this.player = player;
    }

    public Rectangle getGem1() {
        return gem1;
    }

    public Rectangle getGem2() {
        return gem2;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public void setLeader(String leaderName) {
        this.leader = leaderName;
    }
    public ArrayList<Integer> getRoundsScore() {
        return roundsScore;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isPassedRound() {
        return passedRound;
    }

    public void setPassedRound(boolean passedRound) {
        this.passedRound = passedRound;
    }

    public PlayerSet getRivalPlayerSet() {
        return rivalPlayerSet;
    }

    public Row getSiege() {
        return siege;
    }

    public void setSiege(Row siege) {
        this.siege = siege;
    }

    public Row getClose() {
        return close;
    }

    public void setClose(Row close) {
        this.close = close;
    }

    public Row getRanged() {
        return ranged;
    }

    public void setRanged(Row ranged) {
        this.ranged = ranged;
    }

    public String getLeader() {
        return leader;
    }

    public ArrayList<Card> getAllCardsPlaying() {
        return allCardsPlaying;
    }

    public Player getRival() {
        return rival;
    }

    public GameController getGameController() {
        return gameController;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints() {
        totalPoints = siege.getPoints() + close.getPoints() + ranged.getPoints();
        totalPointsLabel.setText(String.valueOf(totalPoints));
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }


    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    public void addCardToDeck(Card card) {
        deck.addCard(card);
        hand.remove(card);
    }

    public Deck getDeck() {
        return deck;
    }

    public void setRivalPlayerSet(PlayerSet rivalPlayerSet) {
        this.rivalPlayerSet = rivalPlayerSet;
        this.rival = rivalPlayerSet.getPlayer();
    }

    public Player getPlayer() {
        return player;
    }



    public ArrayList<Card> getHand() {
        return hand;
    }


    public Label getTotalPointsLabel() {
        return totalPointsLabel;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}