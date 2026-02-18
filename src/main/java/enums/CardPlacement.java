package enums;

public enum CardPlacement {
    SIEGE(21 / 1.5, 706 / 1.5), RANGED_COMBAT(148 / 1.5, 572 / 1.5), CLOSE_COMBAT(289 / 1.5, 441 / 1.5),


    AGILE(0, 0),
    SPECIAL(0, 0),
    SPECIAL_SLOT(0, 0),
    SPELL(160/1.5, 160/1.5),
    WEATHER(0, 0);
    private final double rivalVerticalPosition;
    private final double myVerticalPosition;

    CardPlacement(double rivalVerticalPosition, double myVerticalPosition) {
        this.myVerticalPosition = myVerticalPosition;
        this.rivalVerticalPosition = rivalVerticalPosition;
    }

    public double getMyVerticalPosition() {
        return myVerticalPosition;
    }

    public double getRivalpostition() {
        return rivalVerticalPosition;
    }
}
