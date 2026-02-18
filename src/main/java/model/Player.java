package model;

import java.util.ArrayList;

public class Player {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private int maxScore = 0;
    private int rank = 1;
    private final String securityQuestion;
    private final String securityAnswer;
    private final PlayInfo playInfo = new PlayInfo();
    private PlayerSet playerSet = new PlayerSet(this);
    private static final ArrayList<Player> allPlayers = new ArrayList<>();
    private static Player loggedInPlayer = null;
    private static Player turn;


    public Player(String username, String password, String nickname, String email, String securityQuestion, String securityAnswer) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        allPlayers.add(this);
    }


    public static ArrayList<Player> getAllUsers() {
        return allPlayers;
    }

    public static Player getUserByUsername(String text) {
        for (Player player : allPlayers) {
            if (player.getUsername().equals(text)) {
                return player;
            }
        }
        return null;
    }

    public PlayerSet getPlayerSet() {
        return playerSet;
    }

    public static void setLoggedInUser(Player player) {
        loggedInPlayer = player;
        turn = player;
    }

    public static void logout() {
        loggedInPlayer = null;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String text) {
        this.email = text;
    }

    public void setNickname(String text) {
        this.nickname = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static Player getLoggedInUser() {
        return loggedInPlayer;
    }

    public static void login(Player player) {
        loggedInPlayer = player;
    }


    public static void deleteAccount() {
        allPlayers.remove(loggedInPlayer);
        loggedInPlayer = null;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public PlayInfo getPlayInfo() {
        return playInfo;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getRank() {
        int rank = 1;
        for (Player player : getAllUsers()) {
            if (player.maxScore > this.maxScore) rank++;
        }
        return rank;
    }

    public static Player getTurn() {
        return turn;
    }

    public static void setTurn(Player turn) {
        Player.turn = turn;
    }

}
