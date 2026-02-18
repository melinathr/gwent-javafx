package controller;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.Player;
import model.PlayerSet;

import java.util.concurrent.atomic.AtomicInteger;

public class PreGameController {
    private StackPane currentlySelectedPane = null;

    public String setPlayer2(String username) {
        if (username.isEmpty())
            return "Please Enter a username First";
        else if (!doesUserExist(username))
            return "username doesn't exist.";
        else if (username.equals(Player.getLoggedInUser().getUsername())) {
            return "you can't play with yourself";
        } else {
            setRivalSets(Player.getLoggedInUser(), Player.getUserByUsername(username));
            return "player set";
        }
    }


    private void setRivalSets(Player first, Player second) {
        first.getPlayerSet().setRivalPlayerSet(second.getPlayerSet());
        second.getPlayerSet().setRivalPlayerSet(first.getPlayerSet());
    }

    public void setImageFaction(AtomicInteger selectedFaction, StackPane pane, ImageView imageView, int i) {
        pane.setMaxHeight(imageView.getFitHeight());
        pane.setOnMouseEntered(event -> imageView.setFitWidth(150));
        pane.setOnMouseExited(event -> imageView.setFitWidth(100));
        int finalI = i;
        pane.setOnMouseClicked(event -> {
            if (currentlySelectedPane != null) {
                currentlySelectedPane.setStyle("");
            }
            pane.setStyle("-fx-border-color: green; -fx-border-width: 5px; -fx-border-style: solid;");
            currentlySelectedPane = pane;
            selectedFaction.set(finalI);
        });
    }

    private boolean doesUserExist(String text) {
        for (Player player : Player.getAllUsers()) {
            if (player.getUsername().equals(text)) {
                return true;
            }
        }
        return false;
    }

    public String setFaction(AtomicInteger selectedFaction) {
        if (selectedFaction.get() == -1) return "please select a function";
        else {
            setToGame(selectedFaction, "Skellige", "Scoia'tael", "NorthernRealms", "Nilfgaard", "Monster");
            return "ok";
        }
    }

    private void setToGame(AtomicInteger selectedFaction, String s1, String s2, String s3, String s4, String s5) {
        PlayerSet playerSet = Player.getTurn().getPlayerSet();
        if (selectedFaction.get() == 0) playerSet.setFaction(s1);
        else if (selectedFaction.get() == 1) playerSet.setFaction(s2);
        else if (selectedFaction.get() == 2) playerSet.setFaction(s3);
        else if (selectedFaction.get() == 3) playerSet.setFaction(s4);
        else if (selectedFaction.get() == 4) playerSet.setFaction(s5);
    }

    private void setLeader(AtomicInteger selectedLeader, String l1, String l2, String l3, String l4, String l5) {
        PlayerSet playerSet = Player.getTurn().getPlayerSet();
        switch (selectedLeader.get()) {
            case 0 -> playerSet.setLeader(l1);
            case 1 -> playerSet.setLeader(l2);
            case 2 -> playerSet.setLeader(l3);
            case 3 -> playerSet.setLeader(l4);
            case 4 -> playerSet.setLeader(l5);
        }
    }


    public String setLeader(AtomicInteger selectedLeader) {
        if (selectedLeader.get() == -1) return "please select a leader";
        switch (Player.getTurn().getPlayerSet().getFaction()) {
            case "Monster" ->
                    setLeader(selectedLeader, "BringerOfDeath", "KingOfTheWildHunt", "DestroyerOfWorlds", "CommanderOfTheRedRiders", "TheTreacherous");
            case "Nilfgaard" ->
                    setLeader(selectedLeader, "TheWhiteFlame", "HisImperialMajesty", "EmperorOfNilfgaard", "TheRelentless", "InvaderOfTheNorth");
            case "NorthernRealms" ->
                    setLeader(selectedLeader, "TheSiegemaster", "TheSteel-Forged", "KingOfTemeria", "LordCommanderOfTheNorth", "SonOfMedell");
            case "Scoia'tael" ->
                    setLeader(selectedLeader, "TheBeautiful", "QueenOfDolBlathanna", "PurebloodElf", "DaisyOfTheValle", "HopeOfTheAenSeidhe");
            case "Skellige" ->
                    setLeader(selectedLeader, "CrachAnCraite", "KingBran", "KingBran", "KingBran","KingBran");
        }
        return "ok";
    }



}