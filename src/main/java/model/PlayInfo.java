package model;

public class PlayInfo {
    private int NumberOfAllGames = 0;
    private int NumberOfWins = 0;
    private int NumberOfLost = 0;
    private int NumberOfDraw = 0;

    public int getNumberOfAllGames() {
        return NumberOfAllGames;
    }

    public void addNumberOfAllGames() {
        NumberOfAllGames ++;
    }

    public int getNumberOfWins() {
        return NumberOfWins;
    }

    public void addNumberOfWins() {
        NumberOfWins ++;
    }

    public int getNumberOfLost() {
        return NumberOfLost;
    }

    public void addNumberOfLost() {
        NumberOfLost ++;
    }

    public int getNumberOfDraw() {
        return NumberOfDraw;
    }

    public void addNumberOfDraw(int numberOfDraw) {
        NumberOfDraw ++;
    }
}
