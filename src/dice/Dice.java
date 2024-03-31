package dice;

public class Dice {
    private final int MIN = 1;
    private final int MAX = 6;
    
    public int diceRoll() {
        double d = Math.random() * (MAX - MIN + 1) + MIN;
        return (int)d;
    }
}
